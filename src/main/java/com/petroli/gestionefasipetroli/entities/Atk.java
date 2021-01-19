package com.petroli.gestionefasipetroli.entities;

import javax.persistence.*;

@Entity
public class Atk {

    @Id
    @GeneratedValue
    private long idatk;

    @Column(unique = true)
    private String codice;

    @Column(unique = true)
    private String targa;


    private String suggerito;


    public String getTarga() {
        return targa;
    }

    public long getIdatk() {
        return idatk;
    }

    public String getCodice() {
        return codice;
    }

    public String getSuggerito() {
        return suggerito;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public void setIdatk(long idatk) {
        this.idatk = idatk;
    }

    public void setSuggerito(String suggerito) {
        this.suggerito = suggerito;
    }

    @Override
    public int hashCode() {
        return Math.toIntExact(idatk);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!(obj instanceof Atk)) return false;
        Atk other = (Atk) obj;
        return this.idatk == other.getIdatk();
    }
}
