package com.petroli.gestionefasipetroli.dto;

import java.util.List;

public class ContainerRiepilogoPerFrontend {

    List<RiepilogoPerFrontend> data;
    int numero;

    public int getNumero() {
        return numero;
    }

    public List<RiepilogoPerFrontend> getData() {
        return data;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setData(List<RiepilogoPerFrontend> data) {
        this.data = data;
    }
}
