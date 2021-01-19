package com.petroli.gestionefasipetroli.dto;

import com.petroli.gestionefasipetroli.entities.Trasporto;

import java.util.Set;

public class Viaggio {

    private Set<Trasporto> listaviaggi;


    public Set<Trasporto> getListaviaggi() {
        return listaviaggi;
    }

    public void setListaviaggi(Set<Trasporto> listaviaggi) {
        this.listaviaggi = listaviaggi;
    }
}
