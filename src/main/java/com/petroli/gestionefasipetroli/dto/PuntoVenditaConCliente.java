package com.petroli.gestionefasipetroli.dto;

import com.petroli.gestionefasipetroli.entities.PuntoVendita;

public class PuntoVenditaConCliente {

    private PuntoVendita puntovendita;

    private String nomecliente;


    public String getNomecliente() {
        return nomecliente;
    }

    public PuntoVendita getPuntovendita() {
        return puntovendita;
    }

    public void setNomecliente(String nomecliente) {
        this.nomecliente = nomecliente;
    }

    public void setPuntovendita(PuntoVendita puntovendita) {
        this.puntovendita = puntovendita;
    }
}
