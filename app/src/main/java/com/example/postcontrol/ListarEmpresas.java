package com.example.postcontrol;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;

import com.example.postcontrol.adapter.EmpresaAdapter;
import com.example.postcontrol.entity.Empresa;
import com.example.postcontrol.persistencia.EmpresaDatabase;
import com.example.postcontrol.utils.UtilsGUI;

import java.util.ArrayList;
import java.util.Comparator;

public class ListarEmpresas extends AppCompatActivity {

    private ListView listView;
    private EmpresaAdapter listAdapter;
    private ActionMode actionMode;
    private View viewSelecionada;
    private ArrayList<Empresa> empresas;
    private boolean opcao = true;
    private int posicaoSelecionada = -1;

    private static final String ARQUIVO = "com.example.postcontrol.SORT_PREFERENCE";
    private static final String SORT_PREFERENCE = "SORT_PREFERENCE";

    private final ActionMode.Callback menuActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.empresa_opcoes, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @SuppressLint("NonConstantResourceId")
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            Empresa empresa = (Empresa) listView.getItemAtPosition(posicaoSelecionada);

            switch (item.getItemId()) {
                case R.id.menuItemEditar:
                    editarEmpresa(empresa);
                    mode.finish();
                    posicaoSelecionada = -1;
                    return true;
                case R.id.menuItemExcluir:
                    excluirEmpresa(empresa);
                    mode.finish();
                    posicaoSelecionada = -1;
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            if (viewSelecionada != null) {
                viewSelecionada.setBackgroundColor(Color.TRANSPARENT);
            }
            actionMode = null;
            viewSelecionada = null;
            listView.setEnabled(true);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_empresas);
        listView = findViewById(R.id.list_view_main);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Empresa empresa = (Empresa) parent.getItemAtPosition(position);
            editarEmpresa(empresa);
        });

        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            if (actionMode != null) {
                return false;
            }
            posicaoSelecionada = position;
            view.setBackgroundColor(Color.LTGRAY);
            viewSelecionada = view;
            listView.setEnabled(false);
            actionMode = startSupportActionMode(menuActionModeCallback);
            return true;
        });
        popularLista();
        lerPreferenciaSort();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.listar_empresas_opcoes, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item;

        if (opcao) {
            item = menu.findItem(R.id.menuItemAscendente);
        } else {
            item = menu.findItem(R.id.menuItemDescendente);
        }

        item.setChecked(true);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.menuItemAdicionar:
                CadastrarEmpresa.novaEmpresa(this);
                return true;
            case R.id.menuItemSobre:
                intent = new Intent(this, MostrarAutoria.class);
                startActivity(intent);
                return true;
            case R.id.menuItemAscendente:
                salvarPreferenciaSort(true);
                return true;
            case R.id.menuItemDescendente:
                salvarPreferenciaSort(false);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            popularLista();
            ordenarLista(opcao);
        }
    }

    private void popularLista() {
        EmpresaDatabase empresaDatabase = EmpresaDatabase.getDatabase(this);
        empresas = (ArrayList<Empresa>) empresaDatabase.empresaDAO().findAll();
        listAdapter = new EmpresaAdapter(this, empresas);
        listView.setAdapter(listAdapter);
        lerPreferenciaSort();
    }

    private void excluirEmpresa(final Empresa empresa) {
        String mensagem = getString(R.string.confirmacao_delete) + empresa.getNomeEmpresa();

        DialogInterface.OnClickListener listener = (dialog, which) -> {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    EmpresaDatabase empresaDatabase = EmpresaDatabase.getDatabase(ListarEmpresas.this);
                    empresaDatabase.empresaDAO().delete(empresa);
                    popularLista();
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    break;
            }
        };

        UtilsGUI.confirmaAcao(this, mensagem, listener);
    }

    private void editarEmpresa(final Empresa empresa) {
        CadastrarEmpresa.editarEmpresa(this, empresa);
        popularLista();
    }

    private void lerPreferenciaSort() {
        SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO, Context.MODE_PRIVATE);
        opcao = sharedPreferences.getBoolean(SORT_PREFERENCE, opcao);
        ordenarLista(opcao);
    }

    private void salvarPreferenciaSort(boolean sort) {
        SharedPreferences shared = getSharedPreferences(ARQUIVO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putBoolean(SORT_PREFERENCE, sort);
        editor.apply();
        opcao = sort;
        ordenarLista(sort);
    }

    private void ordenarLista(boolean ascendente) {
        if (ascendente) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                empresas.sort(Comparator.comparing(Empresa::getNomeEmpresa));
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                empresas.sort(Comparator.comparing(Empresa::getNomeEmpresa).reversed());
            }
        }
        listAdapter.notifyDataSetChanged();
    }
}