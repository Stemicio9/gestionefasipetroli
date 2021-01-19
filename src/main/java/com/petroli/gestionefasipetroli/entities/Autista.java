package com.petroli.gestionefasipetroli.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Autista {

    @Id
    @GeneratedValue
    private long idautista;

    @Column(unique = true)
    private String nomeautista;


    public long getIdautista() {
        return idautista;
    }

    public String getNomeautista() {
        return nomeautista;
    }

    public void setIdautista(long idautista) {
        this.idautista = idautista;
    }

    public void setNomeautista(String nomeautista) {
        this.nomeautista = nomeautista;
    }

}
