package com.petroli.gestionefasipetroli.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class VoceDiRettifica {

    @Id
    @GeneratedValue
    private long idvocedirettifica;

    @Column(unique = true)
    private String nomevoce;

    // TRUE = AGGIUNTA, FALSE = SOTTRATTA
    private boolean aggiuntasottratta;

    public long getIdvocedirettifica() {
        return idvocedirettifica;
    }

    public String getNomevoce() {
        return nomevoce;
    }

    public boolean isAggiuntasottratta() {
        return aggiuntasottratta;
    }

    public void setIdvocedirettifica(long idvocedirettifica) {
        this.idvocedirettifica = idvocedirettifica;
    }

    public void setNomevoce(String nomevoce) {
        this.nomevoce = nomevoce;
    }

    public void setAggiuntasottratta(boolean aggiuntasottratta) {
        this.aggiuntasottratta = aggiuntasottratta;
    }
}
