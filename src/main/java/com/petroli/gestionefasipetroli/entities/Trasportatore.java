package com.petroli.gestionefasipetroli.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Trasportatore {

    @Id
    @Column(unique = true)
    private String nometrasportatore;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Rimorchio> listarimorchi;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Atk> listaatk;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Autista> listaautisti;


    public List<Rimorchio> getListarimorchi() {
        return listarimorchi;
    }

    public List<Atk> getListaatk() {
        return listaatk;
    }

    public List<Autista> getListaautisti() {
        return listaautisti;
    }





    public String getNometrasportatore() {
        return nometrasportatore;
    }



    public void setListarimorchi(List<Rimorchio> listarimorchi) {
        this.listarimorchi = listarimorchi;
    }

    public void setListaatk(List<Atk> listaatk) {
        this.listaatk = listaatk;
    }

    public void setListaautisti(List<Autista> listaautisti) {
        this.listaautisti = listaautisti;
    }

    public void setNometrasportatore(String nometrasportatore) {
        this.nometrasportatore = nometrasportatore;
    }
}
