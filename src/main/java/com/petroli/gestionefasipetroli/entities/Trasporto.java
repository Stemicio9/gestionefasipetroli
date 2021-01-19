package com.petroli.gestionefasipetroli.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Trasporto {

    @Id
    @GeneratedValue
    private long id;

    private Date datadicaricazione;

    @OneToOne
    private Fabbisogno fabbisogno;

    @OneToOne
    private Atk atk;

    @OneToOne
    private Rimorchio rimorchio;

    @OneToOne
    private Autista autista;

    private String nometrasportatore;


    public long getId() {
        return id;
    }

    public Atk getAtk() {
        return atk;
    }

    public Autista getAutista() {
        return autista;
    }

    public Date getDatadicaricazione() {
        return datadicaricazione;
    }

    public Fabbisogno getFabbisogno() {
        return fabbisogno;
    }

    public Rimorchio getRimorchio() {
        return rimorchio;
    }

    public String getNometrasportatore() {
        return nometrasportatore;
    }

    public void setNometrasportatore(String nometrasportatore) {
        this.nometrasportatore = nometrasportatore;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAtk(Atk atk) {
        this.atk = atk;
    }

    public void setAutista(Autista autista) {
        this.autista = autista;
    }

    public void setDatadicaricazione(Date datadicaricazione) {
        this.datadicaricazione = datadicaricazione;
    }

    public void setFabbisogno(Fabbisogno fabbisogno) {
        this.fabbisogno = fabbisogno;
    }

    public void setRimorchio(Rimorchio rimorchio) {
        this.rimorchio = rimorchio;
    }


    @Override
    public int hashCode() {
        return Math.toIntExact(id);
    }
}
