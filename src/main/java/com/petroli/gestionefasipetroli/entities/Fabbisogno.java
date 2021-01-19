package com.petroli.gestionefasipetroli.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Fabbisogno{


    @Id
    @GeneratedValue
    private long id;

    private Date data;

    @OneToOne
    private PuntoVendita puntoVendita;

    @OneToOne
    private BaseDiCarico basedicarico;

    @OneToOne
    private Fornitore fornitore;

    private boolean preventivoesistente;

    private boolean trasportoesistente;


    private boolean smaltito;

    private double gasolio;
    private double benzina;
    private double supreme;
    private double gpl;


    public long getId() {
        return id;
    }

    public PuntoVendita getPuntoVendita() {
        return puntoVendita;
    }

    public Date getData() {
        return data;
    }

    public BaseDiCarico getBasedicarico() {
        return basedicarico;
    }

    public double getBenzina() {
        return benzina;
    }


    public double getGasolio() {
        return gasolio;
    }

    public double getGpl() {
        return gpl;
    }

    public double getSupreme() {
        return supreme;
    }

    public Fornitore getFornitore() {
        return fornitore;
    }

    public boolean isSmaltito() {
        return smaltito;
    }

    public boolean isPreventivoesistente() {
        return preventivoesistente;
    }

    public boolean isTrasportoesistente() {
        return trasportoesistente;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPuntoVendita(PuntoVendita puntoVendita) {
        this.puntoVendita = puntoVendita;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setBasedicarico(BaseDiCarico basedicarico) {
        this.basedicarico = basedicarico;
    }

    public void setBenzina(double benzina) {
        this.benzina = benzina;
    }

    public void setFornitore(Fornitore fornitore) {
        this.fornitore = fornitore;
    }

    public void setGasolio(double gasolio) {
        this.gasolio = gasolio;
    }

    public void setGpl(double gpl) {
        this.gpl = gpl;
    }

    public void setSupreme(double supreme) {
        this.supreme = supreme;
    }

    public void setSmaltito(boolean smaltito) {
        this.smaltito = smaltito;
    }

    public void setPreventivoesistente(boolean preventivoesistente) {
        this.preventivoesistente = preventivoesistente;
    }

    public void setTrasportoesistente(boolean trasportoesistente) {
        this.trasportoesistente = trasportoesistente;
    }

}
