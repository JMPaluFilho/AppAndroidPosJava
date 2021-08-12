package com.example.postcontrol;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.postcontrol.entity.Empresa;
import com.example.postcontrol.services.MaskCNPJService;
import com.example.postcontrol.services.MaskCurrencyService;
import com.example.postcontrol.utils.MethodsUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CadastrarEmpresa extends AppCompatActivity {

    private EditText editTextNomeEmpresa, editTextDtInicio, editTextValorContrato, editTextCNPJ;
    private DatePickerDialog datePickerDialog;
    private RadioGroup radioGroupServicos;
    private Spinner spinnerFrequencia;
    private CheckBox contratoAtivo;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_empresa);

        editTextNomeEmpresa = findViewById(R.id.editTextNomeEmpresa);
        editTextDtInicio = findViewById(R.id.editTextDtInicio);
        editTextValorContrato = findViewById(R.id.editTextValorContrato);
        editTextCNPJ = findViewById(R.id.editTextCNPJ);
        contratoAtivo = findViewById(R.id.checkboxContratoAtivo);
        radioGroupServicos = findViewById(R.id.radioGroupServicos);
        spinnerFrequencia = findViewById(R.id.spinnerFrequencia);

        editTextValorContrato.addTextChangedListener(
                new MaskCurrencyService(editTextValorContrato));
        editTextCNPJ.addTextChangedListener(
                MaskCNPJService.insert("##.###.###/####-##", editTextCNPJ));

        popularSpinner();
        setDateTimeField();

        editTextDtInicio.setOnTouchListener((v, event) -> {
            datePickerDialog.show();
            return false;
        });
    }

    private void popularSpinner() {
        ArrayList<String> listaFrequencia = new ArrayList<>();

        listaFrequencia.add(getString(R.string.spinnerVazio));
        listaFrequencia.add(getString(R.string.spinnerUm));
        listaFrequencia.add(getString(R.string.spinnerDois));
        listaFrequencia.add(getString(R.string.spinnerTres));
        listaFrequencia.add(getString(R.string.spinnerQuatro));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, listaFrequencia);

        spinnerFrequencia.setAdapter(adapter);
    }

    private void setDateTimeField() {
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(
                this, (view, year, monthOfYear, dayOfMonth) -> {
            Calendar newDate = Calendar.getInstance();
            newDate.set(year, monthOfYear, dayOfMonth);
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
            final Date startDate = newDate.getTime();
            String fdate = sd.format(startDate);

            editTextDtInicio.setText(fdate);
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH),
                newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    public void limparCampos(View view) {
        editTextNomeEmpresa.setText(null);
        editTextDtInicio.setText(null);
        editTextValorContrato.setText(null);
        editTextCNPJ.setText(null);
        contratoAtivo.setChecked(false);
        radioGroupServicos.clearCheck();
        popularSpinner();

        editTextNomeEmpresa.requestFocus();
        Toast.makeText(this, R.string.campos_limpos, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("NonConstantResourceId")
    public void salvar(View view) {
        String nomeEmpresa = editTextNomeEmpresa.getText().toString();
        String dtInicio = editTextDtInicio.getText().toString();
        String valorContrato = editTextValorContrato.getText().toString();
        String CNPJ = editTextCNPJ.getText().toString();
        String servico;
        String frequencia = (String) spinnerFrequencia.getSelectedItem();
        boolean ativo = contratoAtivo.isChecked();

        switch (radioGroupServicos.getCheckedRadioButtonId()) {
            case R.id.radioButtonPosts:
                servico = getString(R.string.rbInstagram);
                break;
            case R.id.radioButtonReels:
                servico = getString(R.string.rbFacebook);
                break;
            case R.id.radioButtonStories:
                servico = getString(R.string.rbSite);
                break;
            default:
                servico = getString(R.string.erro_servico);
                break;
        }

        boolean erro = validarErros(
                nomeEmpresa, dtInicio, valorContrato, CNPJ, servico, frequencia);

        if (!erro) {
            LocalDate dataInicio = MethodsUtils.converterData(dtInicio);
            Double valor = MethodsUtils.converterValor(valorContrato);

            Empresa empresa = new Empresa(nomeEmpresa, dataInicio, valor, CNPJ, servico, frequencia, ativo);
            Toast.makeText(this, empresa.getDetails(), Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, ListarEmpresas.class);
            intent.putExtra(getString(R.string.empresa), empresa);

            startActivity(intent);
        }
    }

    private boolean validarErros(String nomeEmpresa, String dtInicio, String valorContrato,
                                 String CNPJ, String servico, String frequencia) {

        if (nomeEmpresa.trim().isEmpty()) {
            Toast.makeText(this, R.string.erro_nome, Toast.LENGTH_SHORT).show();
            editTextNomeEmpresa.requestFocus();
            return true;
        }

        if (dtInicio.trim().isEmpty()) {
            Toast.makeText(this, R.string.erro_dtInicio, Toast.LENGTH_SHORT).show();
            return true;
        }

        if (valorContrato.trim().isEmpty()) {
            Toast.makeText(this, R.string.erro_valorContrato, Toast.LENGTH_SHORT).show();
            editTextValorContrato.requestFocus();
            return true;
        }

        if (CNPJ.trim().isEmpty()) {
            Toast.makeText(this, R.string.erro_CNPJ_vazio, Toast.LENGTH_SHORT).show();
            editTextCNPJ.requestFocus();
            return true;
        }

        String unmaskedCNPJ = MaskCNPJService.unmask(CNPJ);
        if (unmaskedCNPJ.length() != 14) {
            Toast.makeText(this, R.string.erro_CNPJ, Toast.LENGTH_SHORT).show();
            editTextCNPJ.requestFocus();
            return true;
        }

        if (servico.equals(getString(R.string.erro_servico))) {
            Toast.makeText(this, servico, Toast.LENGTH_SHORT).show();
            radioGroupServicos.requestFocus();
            return true;
        }

        if (frequencia.isEmpty()) {
            Toast.makeText(this, R.string.erro_frequencia, Toast.LENGTH_SHORT).show();
            spinnerFrequencia.requestFocus();
            return true;
        }

        return false;
    }
}