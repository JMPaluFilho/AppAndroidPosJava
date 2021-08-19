package com.example.postcontrol;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;

import com.example.postcontrol.entity.Empresa;

import java.util.ArrayList;

public class ListarEmpresas extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<Empresa> listAdapter;
    private ActionMode actionMode;
    private View viewSelecionada;
    private int posicaoSelecionada = -1;
    private ArrayList<Empresa> empresas;


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
            switch (item.getItemId()) {
                case R.id.menuItemEditar:
                    editarEmpresa();
                    mode.finish();
                    return true;
                case R.id.menuItemExcluir:
                    excluirEmpresa();
                    mode.finish();
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
            posicaoSelecionada = position;
            editarEmpresa();
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.listar_empresas_opcoes, menu);
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            Bundle bundle = data.getExtras();
            Empresa empresa = (Empresa) bundle.getSerializable(getString(R.string.empresa));

            if (requestCode == CadastrarEmpresa.EDITAR) {
                Empresa empresaExistente = empresas.get(posicaoSelecionada);
                empresaExistente.setNomeEmpresa(empresa.getNomeEmpresa());
                empresaExistente.setDtInicioContrato(empresa.getDtInicioContrato());
                empresaExistente.setValorContrato(empresa.getValorContrato());
                empresaExistente.setCnpj(empresa.getCnpj());
                empresaExistente.setServicoContratado(empresa.getServicoContratado());
                empresaExistente.setFrequenciaSemanal(empresa.getFrequenciaSemanal());
                empresaExistente.setContratoAtivo(empresa.getContratoAtivo());
                posicaoSelecionada = -1;
            } else {
                empresas.add(empresa);
            }
            listAdapter.notifyDataSetChanged();
        }
    }

    private void popularLista() {
        empresas = new ArrayList<>();
        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, empresas);
        listView.setAdapter(listAdapter);
    }

    private void excluirEmpresa() {
        empresas.remove(posicaoSelecionada);
        listAdapter.notifyDataSetChanged();
    }

    private void editarEmpresa() {
        Empresa empresa = empresas.get(posicaoSelecionada);
        CadastrarEmpresa.editarEmpresa(this, empresa);
    }
}