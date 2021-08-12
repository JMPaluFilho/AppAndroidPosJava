package com.example.postcontrol;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.postcontrol.entity.Empresa;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListarEmpresas extends AppCompatActivity {

    private FloatingActionButton btnAdd, btnInfo;
    private RecyclerView recyclerView;
    private static ArrayList<Empresa> empresas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_empresas);

        btnAdd = findViewById(R.id.button_add);
        btnInfo = findViewById(R.id.button_info);
        recyclerView = findViewById(R.id.recycler_view_main);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            Empresa empresa = (Empresa) bundle.getSerializable(getString(R.string.empresa));
            popularLista(empresa);
        }

//        listViewEmpresas = findViewById(R.id.listViewEmpresas);
//
//        listViewEmpresas.setOnItemClickListener((adapterView, view, position, l) -> {
//            Empresa empresa = (Empresa) listViewEmpresas.getItemAtPosition(position);
//            Toast.makeText(getApplicationContext(), empresa.getDetails(), Toast.LENGTH_LONG).show();
//        });

//
    }

    public void adicionarEmpresa(View view) {
        Intent intent = new Intent(this, CadastrarEmpresa.class);
        startActivity(intent);
    }

    public void mostrarInfos(View view) {
        Toast.makeText(this, empresas.get(0).getDetails(), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MostrarAutoria.class);
        startActivity(intent);
    }

    private void popularLista(Empresa empresa) {
       empresas.add(empresa);

        /**
         * criar um Adapter View Holder
         * https://www.youtube.com/watch?v=SgquCAp0yls
         */

/*        ArrayAdapter<Empresa> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, empresas);

        recyclerView.setAdapter(adapter);*/
    }
}