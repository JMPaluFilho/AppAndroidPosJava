package com.example.postcontrol.entity;

public class Empresa {

    private String nomeEmpresa;
    /*
    private Date dtInicioContrato;
    private Double valorContrato;
     */
    private String cnpj;
    private String servicoContratado;
    private Integer frequenciaSemanal;
    /*
    private Boolean contratoAtivo;
    */

    public Empresa(String nomeEmpresa, String cnpj, String servicoContratado, Integer frequenciaSemanal) {
        this.nomeEmpresa = nomeEmpresa;
        this.cnpj = cnpj;
        this.servicoContratado = servicoContratado;
        this.frequenciaSemanal = frequenciaSemanal;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
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

    public Integer getFrequenciaSemanal() {
        return frequenciaSemanal;
    }

    public void setFrequenciaSemanal(Integer frequenciaSemanal) {
        this.frequenciaSemanal = frequenciaSemanal;
    }

    @Override
    public String toString() {
        return "Nome: " + getNomeEmpresa() + "; CNPJ: " + getCnpj();
    }

    public String getDetails() {
        return "Nome: " + getNomeEmpresa()
                + "\nCNPJ: " + getCnpj()
                + "\nServiço Contratado: " + getServicoContratado()
                + "\nFrequência Semanal: " + getFrequenciaSemanal();
    }
}
