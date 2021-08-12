package com.example.postcontrol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.postcontrol.adapter.EmpresaAdapter;
import com.example.postcontrol.entity.Empresa;

import java.util.ArrayList;

public class ListarEmpresas extends AppCompatActivity {

    private RecyclerView recyclerView;
    private static final ArrayList<Empresa> empresas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_empresas);
        recyclerView = findViewById(R.id.recycler_view_main);
    }

    public void adicionarEmpresa(View view) {
        Intent intent = new Intent(this, CadastrarEmpresa.class);
        intent.putExtra(getString(R.string.result), 1);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                Empresa empresa = (Empresa) bundle.getSerializable(getString(R.string.empresa));
                popularLista(empresa);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void mostrarInfos(View view) {
        Intent intent = new Intent(this, MostrarAutoria.class);
        startActivity(intent);
    }

    private void popularLista(Empresa empresa) {
        empresas.add(empresa);
        EmpresaAdapter empresaAdapter = new EmpresaAdapter(empresas);
        recyclerView.setAdapter(empresaAdapter);
    }
}