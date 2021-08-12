package com.example.postcontrol.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Empresa implements Serializable {

    private static final long serialVersionUID = -6609754280778852186L;

    private String nomeEmpresa;
    private LocalDate dtInicioContrato;
    private Double valorContrato;
    private String cnpj;
    private String servicoContratado;
    private String frequenciaSemanal;
    private Boolean contratoAtivo;

    public Empresa() {

    }

    public Empresa(String nomeEmpresa, LocalDate dtInicioContrato, Double valorContrato, String cnpj,
                   String servicoContratado, String frequenciaSemanal, Boolean contratoAtivo) {
        this.nomeEmpresa = nomeEmpresa;
        this.dtInicioContrato = dtInicioContrato;
        this.valorContrato = valorContrato;
        this.cnpj = cnpj;
        this.servicoContratado = servicoContratado;
        this.frequenciaSemanal = frequenciaSemanal;
        this.contratoAtivo = contratoAtivo;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public LocalDate getDtInicioContrato() {
        return dtInicioContrato;
    }

    public void setDtInicioContrato(LocalDate dtInicioContrato) {
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
        return "Nome: " + getNomeEmpresa() + "; CNPJ: " + getCnpj();
    }

    public String getDetails() {
        return "Nome: " + getNomeEmpresa()
                + "\nData de Início do Contrato: " + getDtInicioContrato().
                format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                + "\nValor do Contrato: " + getValorContrato()
                + "\nCNPJ: " + getCnpj()
                + "\nServiço Contratado: " + getServicoContratado()
                + "\nFrequência Semanal: " + getFrequenciaSemanal()
                + "\nContrato Ativo: " + (getContratoAtivo() ? "Sim" : "Não");
    }
}
