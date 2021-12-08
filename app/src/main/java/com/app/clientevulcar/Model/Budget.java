package com.app.clientevulcar.Model;

import java.util.Date;

public class Budget {
    private String id, idCliente, nomeCliente, idLoja, nomeLoja, nomeStatus, thing, idPag;
    private String dtOrcamento;
    private int sts;

    public String getThing() {
        return thing;
    }

    public void setThing(String thing) {
        this.thing = thing;
    }

    public String getIdPag() {
        return idPag;
    }

    public void setIdPag(String idPag) {
        this.idPag = idPag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getIdLoja() {
        return idLoja;
    }

    public void setIdLoja(String idLoja) {
        this.idLoja = idLoja;
    }

    public String getNomeLoja() {
        return nomeLoja;
    }

    public void setNomeLoja(String nomeLoja) {
        this.nomeLoja = nomeLoja;
    }

    public String getNomeStatus() {
        return nomeStatus;
    }

    public void setNomeStatus(String nomeStatus) {
        this.nomeStatus = nomeStatus;
    }

    public String getDtOrcamento() {
        return dtOrcamento;
    }

    public void setDtOrcamento(String dtOrcamento) {
        this.dtOrcamento = dtOrcamento;
    }

    public int getSts() {
        return sts;
    }

    public void setSts(int sts) {
        this.sts = sts;
    }
}
