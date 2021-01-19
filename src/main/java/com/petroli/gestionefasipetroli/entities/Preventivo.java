package com.petroli.gestionefasipetroli.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Preventivo{

    @Id
    @GeneratedValue
    private long id;

    private Date data;

    private String nomecliente;

    @OneToOne
    private Fabbisogno riferimento;

    private double prezzoalpubblicogasolioservito;
    private double prezzoalpubblicogasolioself;

    private double prezzoalpubblicobenzinaservito;
    private double prezzoalpubblicobenzinaself;

    private double prezzoalpubblicosupremeservito;
    private double prezzoalpubblicosupremeself;

    private double prezzoalpubblicogplservito;
    private double prezzoalpubblicogplself;

    private double marginecessionegasolio;
    private double marginecessionebenzina;
    private double marginecessionesupreme;
    private double marginecessionegpl;

    @OneToMany(fetch = FetchType.EAGER)
    private List<VoceDiRettificaConValore> listavocidirettifica = new LinkedList<>();

    public long getId() {
        return id;
    }

    public String getNomecliente() {
        return nomecliente;
    }

    public Date getData() {
        return data;
    }

    public Fabbisogno getRiferimento() {
        return riferimento;
    }

    public double getPrezzoalpubblicobenzinaself() {
        return prezzoalpubblicobenzinaself;
    }

    public double getPrezzoalpubblicobenzinaservito() {
        return prezzoalpubblicobenzinaservito;
    }

    public double getPrezzoalpubblicogasolioself() {
        return prezzoalpubblicogasolioself;
    }

    public double getPrezzoalpubblicogasolioservito() {
        return prezzoalpubblicogasolioservito;
    }

    public double getPrezzoalpubblicogplself() {
        return prezzoalpubblicogplself;
    }

    public double getPrezzoalpubblicogplservito() {
        return prezzoalpubblicogplservito;
    }

    public List<VoceDiRettificaConValore> getListavocidirettifica() {
        return listavocidirettifica;
    }

    public double getPrezzoalpubblicosupremeself() {
        return prezzoalpubblicosupremeself;
    }

    public double getPrezzoalpubblicosupremeservito() {
        return prezzoalpubblicosupremeservito;
    }

    public double getMarginecessionebenzina() {
        return marginecessionebenzina;
    }

    public double getMarginecessionegasolio() {
        return marginecessionegasolio;
    }

    public double getMarginecessionegpl() {
        return marginecessionegpl;
    }

    public double getMarginecessionesupreme() {
        return marginecessionesupreme;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setRiferimento(Fabbisogno riferimento) {
        this.riferimento = riferimento;
    }

    public void setPrezzoalpubblicobenzinaself(double prezzoalpubblicobenzinaself) {
        this.prezzoalpubblicobenzinaself = prezzoalpubblicobenzinaself;
    }

    public void setPrezzoalpubblicobenzinaservito(double prezzoalpubblicobenzinaservito) {
        this.prezzoalpubblicobenzinaservito = prezzoalpubblicobenzinaservito;
    }

    public void setListavocidirettifica(List<VoceDiRettificaConValore> listavocidirettifica) {
        this.listavocidirettifica = listavocidirettifica;
    }

    public void setPrezzoalpubblicogasolioself(double prezzoalpubblicogasolioself) {
        this.prezzoalpubblicogasolioself = prezzoalpubblicogasolioself;
    }

    public void setPrezzoalpubblicogasolioservito(double prezzoalpubblicogasolioservito) {
        this.prezzoalpubblicogasolioservito = prezzoalpubblicogasolioservito;
    }

    public void setPrezzoalpubblicogplservito(double prezzoalpubblicogplservito) {
        this.prezzoalpubblicogplservito = prezzoalpubblicogplservito;
    }

    public void setPrezzoalpubblicogplself(double prezzoalpubblicogplself) {
        this.prezzoalpubblicogplself = prezzoalpubblicogplself;
    }

    public void setPrezzoalpubblicosupremeself(double prezzoalpubblicosupremeself) {
        this.prezzoalpubblicosupremeself = prezzoalpubblicosupremeself;
    }

    public void setPrezzoalpubblicosupremeservito(double prezzoalpubblicosupremeservito) {
        this.prezzoalpubblicosupremeservito = prezzoalpubblicosupremeservito;
    }

    public void setMarginecessionebenzina(double marginecessionebenzina) {
        this.marginecessionebenzina = marginecessionebenzina;
    }

    public void setMarginecessionegasolio(double marginecessionegasolio) {
        this.marginecessionegasolio = marginecessionegasolio;
    }

    public void setMarginecessionegpl(double marginecessionegpl) {
        this.marginecessionegpl = marginecessionegpl;
    }

    public void setMarginecessionesupreme(double marginecessionesupreme) {
        this.marginecessionesupreme = marginecessionesupreme;
    }

    public void setNomecliente(String nomecliente) {
        this.nomecliente = nomecliente;
    }
}
