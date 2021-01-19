package com.petroli.gestionefasipetroli.dto;

import com.petroli.gestionefasipetroli.entities.Preventivo;
import com.petroli.gestionefasipetroli.entities.VoceDiRettificaConValore;

import java.util.List;

public class ContenitorePreventivoVoci {

    Preventivo preventivo;
    List<VoceDiRettificaConValore> dareinserirenelpuntovendita;


    public Preventivo getPreventivo() {
        return preventivo;
    }

    public List<VoceDiRettificaConValore> getDareinserirenelpuntovendita() {
        return dareinserirenelpuntovendita;
    }

    public void setPreventivo(Preventivo preventivo) {
        this.preventivo = preventivo;
    }

    public void setDareinserirenelpuntovendita(List<VoceDiRettificaConValore> dareinserirenelpuntovendita) {
        this.dareinserirenelpuntovendita = dareinserirenelpuntovendita;
    }
}
