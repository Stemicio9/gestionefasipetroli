package com.petroli.gestionefasipetroli.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Impostazioni {

    @Id
    private String nomeimpostazione;

    private String valoreimpostazione;

    public String getNomeimpostazione() {
        return nomeimpostazione;
    }

    public String getValoreimpostazione() {
        return valoreimpostazione;
    }

    public void setNomeimpostazione(String nomeimpostazione) {
        this.nomeimpostazione = nomeimpostazione;
    }

    public void setValoreimpostazione(String valoreimpostazione) {
        this.valoreimpostazione = valoreimpostazione;
    }
}
