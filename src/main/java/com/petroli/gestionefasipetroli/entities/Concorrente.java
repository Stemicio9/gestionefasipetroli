package com.petroli.gestionefasipetroli.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Concorrente {

    @Id
    @GeneratedValue
    private long id;

    private String nomeconcorrente;

    public String getNomeconcorrente() {
        return nomeconcorrente;
    }

    public long getId() {
        return id;
    }

    public void setNomeconcorrente(String nomeconcorrente) {
        this.nomeconcorrente = nomeconcorrente;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!(obj instanceof Concorrente)) return false;
        Concorrente other = (Concorrente) obj;
        return this.id == other.getId() || this.nomeconcorrente.equals(other.getNomeconcorrente());
    }

    @Override
    public String toString() {
        return this.nomeconcorrente;
    }
}
