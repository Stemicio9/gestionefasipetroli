package com.petroli.gestionefasipetroli.entities;

import javax.persistence.*;

@Entity
public class Rimorchio {

    @Id
    @Column(unique = true)
    private String targa;



    public String getTarga() {
        return targa;
    }



    public void setTarga(String targa) {
        this.targa = targa;
    }
}
