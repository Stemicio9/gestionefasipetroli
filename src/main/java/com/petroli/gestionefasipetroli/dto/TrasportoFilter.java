package com.petroli.gestionefasipetroli.dto;

import com.petroli.gestionefasipetroli.entities.Atk;
import com.petroli.gestionefasipetroli.entities.BaseDiCarico;

import java.util.Date;

public class TrasportoFilter {

    private Atk atk;

    private BaseDiCarico baseDiCarico;

    private Date data;

    private Date datafine;

    public Atk getAtk() {
        return atk;
    }

    public Date getData() {
        return data;
    }

    public BaseDiCarico getBaseDiCarico() {
        return baseDiCarico;
    }

    public Date getDatafine() {
        return datafine;
    }

    public void setAtk(Atk atk) {
        this.atk = atk;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setBaseDiCarico(BaseDiCarico baseDiCarico) {
        this.baseDiCarico = baseDiCarico;
    }

    public void setDatafine(Date datafine) {
        this.datafine = datafine;
    }
}
