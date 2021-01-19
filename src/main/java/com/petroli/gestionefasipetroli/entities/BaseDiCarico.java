package com.petroli.gestionefasipetroli.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BaseDiCarico {

    @Id
    @GeneratedValue
    private long idbasedicarico;

    @Column(unique = true)
    private String nomebasedicarico;


    public long getIdbasedicarico() {
        return idbasedicarico;
    }

    public String getNomebasedicarico() {
        return nomebasedicarico;
    }

    public void setIdbasedicarico(long idbasedicarico) {
        this.idbasedicarico = idbasedicarico;
    }

    public void setNomebasedicarico(String nomebasedicarico) {
        this.nomebasedicarico = nomebasedicarico;
    }

    @Override
    public int hashCode() {
        return Math.toIntExact(idbasedicarico);
    }
}
