package com.example.postcontrol;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.postcontrol.entity.Empresa;

import java.util.ArrayList;

public class ListarEmpresas extends AppCompatActivity {

    private ListView listViewEmpresas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_empresas);

        listViewEmpresas = findViewById(R.id.listViewEmpresas);

        listViewEmpresas.setOnItemClickListener((adapterView, view, position, l) -> {
            Empresa empresa = (Empresa) listViewEmpresas.getItemAtPosition(position);
            Toast.makeText(getApplicationContext(), empresa.getDetails(), Toast.LENGTH_LONG).show();
        });

        popularLista();
    }

    private void popularLista() {
        String[] nomes = getResources().getStringArray(R.array.nomesEmpresas);
        String[] cnpjs = getResources().getStringArray(R.array.cnpjEmpresas);
        String[] servicos = getResources().getStringArray(R.array.servicosEmpresas);
        int[] frequencias = getResources().getIntArray(R.array.frequenciaSemanal);

        ArrayList<Empresa> empresas = new ArrayList<>();

       for (int i = 0; i < nomes.length; i++) {
           empresas.add(new Empresa(nomes[i], cnpjs[i], servicos[i], frequencias[i]));
        }

        ArrayAdapter<Empresa> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, empresas);

        listViewEmpresas.setAdapter(adapter);
    }
}