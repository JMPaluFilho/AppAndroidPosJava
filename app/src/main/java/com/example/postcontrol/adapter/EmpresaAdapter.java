package com.example.postcontrol.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.ColorInt;

import com.example.postcontrol.R;
import com.example.postcontrol.entity.Empresa;
import com.example.postcontrol.utils.MethodsUtils;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class EmpresaAdapter extends BaseAdapter {

    private final Context context;
    private final List<Empresa> empresas;
    private static final HashMap<String, Integer> cores = new HashMap<>();

    private static class EmpresaHolder {
        public TextView txtIcon;
        public TextView txtNome;
        public TextView txtServico;
        public TextView txtValor;
    }

    public EmpresaAdapter(Context context, List<Empresa> empresas) {
        this.context = context;
        this.empresas = empresas;
    }

    private static ShapeDrawable oval(@ColorInt int color, View view) {
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        shapeDrawable.setIntrinsicHeight(view.getHeight());
        shapeDrawable.setIntrinsicWidth(view.getWidth());
        shapeDrawable.getPaint().setColor(color);
        return shapeDrawable;
    }

    @Override
    public int getCount() {
        return empresas.size();
    }

    @Override
    public Object getItem(int position) {
        return empresas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EmpresaHolder empresaHolder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.empresa_item, parent, false);
            empresaHolder = new EmpresaHolder();

            empresaHolder.txtIcon = convertView.findViewById(R.id.txt_icon);
            empresaHolder.txtNome = convertView.findViewById(R.id.txt_nome);
            empresaHolder.txtServico = convertView.findViewById(R.id.txt_servico);
            empresaHolder.txtValor = convertView.findViewById(R.id.txt_valor);

            convertView.setTag(empresaHolder);
        } else {
            empresaHolder = (EmpresaHolder) convertView.getTag();
        }

        empresaHolder.txtIcon.setText(String.valueOf(empresas.get(position).getNomeEmpresa().charAt(0)));
        empresaHolder.txtNome.setText(empresas.get(position).getNomeEmpresa());
        empresaHolder.txtServico.setText(empresas.get(position).getServicoContratado());
        empresaHolder.txtValor.setText(MethodsUtils.exibirValor(empresas.get(position).getValorContrato()));

        preencherCores(empresaHolder, empresas.get(position));

        return convertView;
    }

    private void preencherCores(EmpresaHolder empresaHolder, Empresa empresa) {
        if (!cores.containsKey(empresa.getNomeEmpresa())) {
            int random = 0;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                random = ThreadLocalRandom.current().nextInt();
            }
            empresaHolder.txtIcon.setBackground(oval(Color.rgb(random, random / 2, 0), empresaHolder.txtIcon));
            cores.put(empresa.getNomeEmpresa(), random);
        } else {
            empresaHolder.txtIcon.setBackground(oval(Color.rgb(
                    cores.get(empresa.getNomeEmpresa()), cores.get(empresa.getNomeEmpresa()) / 2, 0), empresaHolder.txtIcon));
        }
    }
}
