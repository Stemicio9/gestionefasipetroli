package com.petroli.gestionefasipetroli.entities;


import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Cliente {

    @Id
    @GeneratedValue
    private long idcliente;

    private String nomecliente;

    private String partitaiva;

    @OneToMany()
    private List<PuntoVendita> listapuntivendita = new LinkedList<>();

    private double marginegasolioservito;
    private double marginegasolioself;

    private double marginebenzinaservito;
    private double marginebenzinaself;

    private double marginesupremeservito;
    private double marginesupremeself;

    private double marginegplservito;
    private double marginegplself;


    public long getIdcliente() {
        return idcliente;
    }

    public String getNomecliente() {
        return nomecliente;
    }

    public String getPartitaiva() {
        return partitaiva;
    }

    public double getMarginebenzinaself() {
        return marginebenzinaself;
    }

    public double getMarginebenzinaservito() {
        return marginebenzinaservito;
    }

    public double getMarginegasolioself() {
        return marginegasolioself;
    }

    public double getMarginegasolioservito() {
        return marginegasolioservito;
    }

    public double getMarginegplself() {
        return marginegplself;
    }

    public double getMarginegplservito() {
        return marginegplservito;
    }

    public double getMarginesupremeself() {
        return marginesupremeself;
    }

    public double getMarginesupremeservito() {
        return marginesupremeservito;
    }

    public List<PuntoVendita> getListapuntivendita() {
        return listapuntivendita;
    }

    public void setIdcliente(long idcliente) {
        this.idcliente = idcliente;
    }

    public void setNomecliente(String nomecliente) {
        this.nomecliente = nomecliente;
    }

    public void setPartitaiva(String partitaiva) {
        this.partitaiva = partitaiva;
    }

    public void setListapuntivendita(List<PuntoVendita> listapuntivendita) {
        this.listapuntivendita = listapuntivendita;
    }

    public void setMarginebenzinaself(double marginebenzinaself) {
        this.marginebenzinaself = marginebenzinaself;
    }

    public void setMarginebenzinaservito(double marginebenzinaservito) {
        this.marginebenzinaservito = marginebenzinaservito;
    }

    public void setMarginegasolioself(double marginegasolioself) {
        this.marginegasolioself = marginegasolioself;
    }

    public void setMarginegasolioservito(double marginegasolioservito) {
        this.marginegasolioservito = marginegasolioservito;
    }

    public void setMarginegplself(double marginegplself) {
        this.marginegplself = marginegplself;
    }

    public void setMarginegplservito(double marginegplservito) {
        this.marginegplservito = marginegplservito;
    }

    public void setMarginesupremeself(double marginesupremeself) {
        this.marginesupremeself = marginesupremeself;
    }

    public void setMarginesupremeservito(double marginesupremeservito) {
        this.marginesupremeservito = marginesupremeservito;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!(obj instanceof Cliente)) return false;
        Cliente that = (Cliente) obj;
        return this.getNomecliente().equals(that.getNomecliente());
     }


    @Override
    public int hashCode() {
        return this.nomecliente.hashCode();
    }
}
