package com.petroli.gestionefasipetroli.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Bonifico {


    @Id
    @GeneratedValue
    private long id;

    private double importobonifico;

    private String descrizione;


    public long getId() {
        return id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public double getImportobonifico() {
        return importobonifico;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setImportobonifico(double importobonifico) {
        this.importobonifico = importobonifico;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!(obj instanceof Bonifico)) return false;
        Bonifico that = (Bonifico) obj;
        return this.id == that.getId();
    }
}
