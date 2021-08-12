package com.example.postcontrol.adapter;

import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.postcontrol.R;
import com.example.postcontrol.entity.Empresa;
import com.example.postcontrol.utils.MethodsUtils;

import java.util.List;

public class EmpresaAdapter extends RecyclerView.Adapter<EmpresaAdapter.EmpresaViewHolder> {

    private final List<Empresa> empresas;

    public EmpresaAdapter(List<Empresa> empresas) {
        this.empresas = empresas;
    }

    @NonNull
    @Override
    public EmpresaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.empresa_item, parent, false);
        return new EmpresaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmpresaViewHolder holder, int position) {
        Empresa empresa = empresas.get(position);
        holder.bind(empresa);
    }

    @Override
    public int getItemCount() {
        return empresas.size();
    }

    private static ShapeDrawable oval(@ColorInt int color, View view) {
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        shapeDrawable.setIntrinsicHeight(view.getHeight());
        shapeDrawable.setIntrinsicWidth(view.getWidth());
        shapeDrawable.getPaint().setColor(color);
        return shapeDrawable;
    }

    static class EmpresaViewHolder extends RecyclerView.ViewHolder {

        TextView txtIcon;
        TextView txtNome;
        TextView txtCnpj;
        TextView txtValor;
        TextView txtDtInicio;

        EmpresaViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIcon = itemView.findViewById(R.id.txt_icon);
            txtNome = itemView.findViewById(R.id.txt_nome);
            txtCnpj = itemView.findViewById(R.id.txt_cnpj);
            txtValor = itemView.findViewById(R.id.txt_valor);
            txtDtInicio = itemView.findViewById(R.id.txt_dtInicio);
        }

        void bind(Empresa empresa) {
            int hash = empresa.getNomeEmpresa().hashCode();
            txtIcon.setText(String.valueOf(empresa.getNomeEmpresa().charAt(0)));
            txtIcon.setBackground(oval(Color.rgb(hash, hash / 2, 0), txtIcon));
            txtNome.setText(empresa.getNomeEmpresa());
            txtCnpj.setText(empresa.getCnpj());
            txtValor.setText(MethodsUtils.exibirValor(empresa.getValorContrato()));
            txtDtInicio.setText(MethodsUtils.exibirData(empresa.getDtInicioContrato()));
        }

    }
}
