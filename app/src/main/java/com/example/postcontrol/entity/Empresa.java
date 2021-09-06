package com.example.postcontrol.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.postcontrol.utils.MethodsUtils;

@Entity
public class Empresa {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String nomeEmpresa;
    private String dtInicioContrato;
    private Double valorContrato;
    private String cnpj;
    private String servicoContratado;
    private String frequenciaSemanal;
    private Boolean contratoAtivo;

    public Empresa() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getDtInicioContrato() {
        return dtInicioContrato;
    }

    public void setDtInicioContrato(String dtInicioContrato) {
        this.dtInicioContrato = dtInicioContrato;
    }

    public Double getValorContrato() {
        return valorContrato;
    }

    public void setValorContrato(Double valorContrato) {
        this.valorContrato = valorContrato;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getServicoContratado() {
        return servicoContratado;
    }

    public void setServicoContratado(String servicoContratado) {
        this.servicoContratado = servicoContratado;
    }

    public String getFrequenciaSemanal() {
        return frequenciaSemanal;
    }

    public void setFrequenciaSemanal(String frequenciaSemanal) {
        this.frequenciaSemanal = frequenciaSemanal;
    }

    public Boolean getContratoAtivo() {
        return contratoAtivo;
    }

    public void setContratoAtivo(Boolean contratoAtivo) {
        this.contratoAtivo = contratoAtivo;
    }

    @Override
    public String toString() {
        return getNomeEmpresa()
                + "\n" + getServicoContratado()
                + "\n" + MethodsUtils.exibirValor(getValorContrato());
    }

    public String getDetails() {
        return getId()
                + "\n" + getNomeEmpresa()
                + "\n" + getDtInicioContrato()
                + "\n" + MethodsUtils.exibirValor(getValorContrato())
                + "\n" + getCnpj()
                + "\n" + getServicoContratado()
                + "\n" + getFrequenciaSemanal()
                + "\n" + getContratoAtivo();
    }
}
