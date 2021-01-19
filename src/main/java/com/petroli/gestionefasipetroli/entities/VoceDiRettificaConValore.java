package com.petroli.gestionefasipetroli.entities;

import javax.persistence.*;

@Entity
public class VoceDiRettificaConValore {

    @Id
    @GeneratedValue
    private long id;


    private String vocedirettifica;

    // true = + , false = -
    private boolean segno;

    private double valore;

    private String descrizione;


    public long getId() {
        return id;
    }

    public String getVocedirettifica() {
        return vocedirettifica;
    }

    public double getValore() {
        return valore;
    }

    public boolean isSegno() {
        return segno;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setVocedirettifica(String vocedirettifica) {
        this.vocedirettifica = vocedirettifica;
    }

    public void setValore(double valore) {
        this.valore = valore;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setSegno(boolean segno) {
        this.segno = segno;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
