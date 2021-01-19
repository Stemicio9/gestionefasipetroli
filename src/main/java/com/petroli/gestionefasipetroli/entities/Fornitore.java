package com.petroli.gestionefasipetroli.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Fornitore {

    @Id
    @GeneratedValue
    private long idfornitore;

    @Column(unique = true)
    private String nomefornitore;

    private Date datainiziocontratto;

    private Date datafinecontratto;

    @OneToMany(cascade = CascadeType.ALL)
    private List<QuotazioneGiornaliera> quotazioni;


    public Date getDatafinecontratto() {
        return datafinecontratto;
    }

    public Date getDatainiziocontratto() {
        return datainiziocontratto;
    }

    public List<QuotazioneGiornaliera> getQuotazioni() {
        return quotazioni;
    }

    public long getIdfornitore() {
        return idfornitore;
    }


    public String getNomefornitore() {
        return nomefornitore;
    }

    public void setDatafinecontratto(Date datafinecontratto) {
        this.datafinecontratto = datafinecontratto;
    }

    public void setDatainiziocontratto(Date datainiziocontratto) {
        this.datainiziocontratto = datainiziocontratto;
    }

    public void setIdfornitore(long idfornitore) {
        this.idfornitore = idfornitore;
    }

    public void setNomefornitore(String nomefornitore) {
        this.nomefornitore = nomefornitore;
    }

    public void setQuotazioni(List<QuotazioneGiornaliera> quotazioni) {
        this.quotazioni = quotazioni;
    }
}
