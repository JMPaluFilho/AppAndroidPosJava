package com.example.postcontrol;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
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

    public static final String MODO = "MODO";
    public static final String EMPRESA = "EMPRESA";
    public static final int NOVO = 1;
    public static final int EDITAR = 2;

    private EditText editTextNomeEmpresa, editTextDtInicio, editTextValorContrato, editTextCNPJ;
    private DatePickerDialog datePickerDialog;
    private RadioGroup radioGroupServicos;
    private Spinner spinnerFrequencia;
    private CheckBox contratoAtivo;
    private static final ArrayList<String> listaFrequencia = new ArrayList<>();


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_empresa);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

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

        if (listaFrequencia.isEmpty()) {
            popularListaSpinner();
        }

        popularSpinner();
        setDateTimeField();

        editTextDtInicio.setOnTouchListener((v, event) -> {
            datePickerDialog.show();
            return false;
        });

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            int modo = bundle.getInt(MODO, NOVO);

            if (modo == NOVO) {
                setTitle(getString(R.string.nova_empresa));
            }

            if (modo == EDITAR) {
                Empresa empresa = (Empresa) bundle.getSerializable(EMPRESA);
                editTextNomeEmpresa.setText(empresa.getNomeEmpresa());
                editTextDtInicio.setText(MethodsUtils.exibirData(empresa.getDtInicioContrato()));
                editTextValorContrato.setText(String.valueOf(empresa.getValorContrato()));
                editTextCNPJ.setText(empresa.getCnpj());
                contratoAtivo.setChecked(empresa.getContratoAtivo());

                switch (empresa.getServicoContratado()) {
                    case "Instagram":
                        radioGroupServicos.check(R.id.radioButtonPosts);
                        break;
                    case "Facebook":
                        radioGroupServicos.check(R.id.radioButtonReels);
                        break;
                    case "Site":
                        radioGroupServicos.check(R.id.radioButtonStories);
                        break;
                    default:
                        radioGroupServicos.clearCheck();
                        break;
                }

                for (int i = 0; i < listaFrequencia.size(); i++) {
                    if (empresa.getFrequenciaSemanal().equals(listaFrequencia.get(i))) {
                        spinnerFrequencia.setSelection(i);
                    }
                }

                setTitle(getString(R.string.editar_empresa));
            }
        }
    }

    public static void novaEmpresa(AppCompatActivity activity) {
        Intent intent = new Intent(activity, CadastrarEmpresa.class);
        intent.putExtra(MODO, NOVO);
        activity.startActivityForResult(intent, NOVO);
    }

    public static void editarEmpresa(AppCompatActivity activity, Empresa empresa) {
        Intent intent = new Intent(activity, CadastrarEmpresa.class);
        intent.putExtra(MODO, EDITAR);
        intent.putExtra(EMPRESA, empresa);
        activity.startActivityForResult(intent, EDITAR);
    }

    private void popularListaSpinner() {
        listaFrequencia.add(getString(R.string.spinnerVazio));
        listaFrequencia.add(getString(R.string.spinnerUm));
        listaFrequencia.add(getString(R.string.spinnerDois));
        listaFrequencia.add(getString(R.string.spinnerTres));
        listaFrequencia.add(getString(R.string.spinnerQuatro));
    }

    private void popularSpinner() {
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

    public void limparCampos(MenuItem item) {
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
    public void salvar(MenuItem item) {
        String nomeEmpresa = editTextNomeEmpresa.getText().toString();
        String dtInicio = editTextDtInicio.getText().toString();
        String valorContrato = editTextValorContrato.getText().toString();
        String cnpj = editTextCNPJ.getText().toString();
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
                nomeEmpresa, dtInicio, valorContrato, cnpj, servico, frequencia);

        if (!erro) {
            LocalDate dataInicio = MethodsUtils.converterData(dtInicio);
            Double valor = MethodsUtils.converterValor(valorContrato);

            Empresa empresa = new Empresa(nomeEmpresa, dataInicio, valor, cnpj, servico, frequencia, ativo);
            Toast.makeText(this, empresa.getDetails(), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent();
            intent.putExtra(getString(R.string.empresa), empresa);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cadastro_opcoes, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuItemSalvar:
                salvar(item);
                return true;
            case R.id.menuItemLimpar:
                limparCampos(item);
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
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