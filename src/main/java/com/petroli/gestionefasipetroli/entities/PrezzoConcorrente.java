package com.petroli.gestionefasipetroli.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PrezzoConcorrente {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    private Concorrente concorrente;

    private Date data;

    private double prezzobenzinaservito;

    private double prezzogasolioservito;

    private double prezzosupremeservito;



    private double prezzobenzinaself;

    private double prezzogasolioself;

    private double prezzosupremeself;

    private double prezzogpl;


    public long getId() {
        return id;
    }

    public Date getData() {
        return data;
    }

    public double getPrezzosupremeself() {
        return prezzosupremeself;
    }

    public double getPrezzogasolioself() {
        return prezzogasolioself;
    }

    public double getPrezzobenzinaself() {
        return prezzobenzinaself;
    }

    public double getPrezzogpl() {
        return prezzogpl;
    }

    public double getPrezzobenzinaservito() {
        return prezzobenzinaservito;
    }

    public double getPrezzogasolioservito() {
        return prezzogasolioservito;
    }

    public double getPrezzosupremeservito() {
        return prezzosupremeservito;
    }

    public Concorrente getConcorrente() {
        return concorrente;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setPrezzosupremeself(double prezzosupremeself) {
        this.prezzosupremeself = prezzosupremeself;
    }

    public void setPrezzogasolioself(double prezzogasolioself) {
        this.prezzogasolioself = prezzogasolioself;
    }

    public void setPrezzobenzinaself(double prezzobenzinaself) {
        this.prezzobenzinaself = prezzobenzinaself;
    }

    public void setPrezzogpl(double prezzogpl) {
        this.prezzogpl = prezzogpl;
    }

    public void setConcorrente(Concorrente concorrente) {
        this.concorrente = concorrente;
    }

    public void setPrezzobenzinaservito(double prezzobenzinaservito) {
        this.prezzobenzinaservito = prezzobenzinaservito;
    }

    public void setPrezzogasolioservito(double prezzogasolioservito) {
        this.prezzogasolioservito = prezzogasolioservito;
    }

    public void setPrezzosupremeservito(double prezzosupremeservito) {
        this.prezzosupremeservito = prezzosupremeservito;
    }

    @Override
    public String toString() {
        return this.concorrente.getNomeconcorrente() + "   --   DATA: " + this.data.toString();
    }
}
