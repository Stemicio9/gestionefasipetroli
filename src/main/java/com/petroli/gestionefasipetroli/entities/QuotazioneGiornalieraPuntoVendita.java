package com.petroli.gestionefasipetroli.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class QuotazioneGiornalieraPuntoVendita {


    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private Date data;

    private double prezzobenzina;

    private double prezzogasolio;

    private double prezzosupreme;

    private double prezzogpl;


    private double prezzobenzinaself;

    private double prezzogasolioself;

    private double prezzosupremeself;

    private double prezzogplself;

    private double marginecessionegasolio;
    private double marginecessionebenzina;
    private double marginecessionesupreme;
    private double marginecessionegpl;


    private double volumegasolioself;
    private double volumegasolioservito;
    private double volumebenzinaself;
    private double volumebenzinaservito;
    private double volumesupremeself;
    private double volumesupremeservito;
    private double volumegpl;

    private double trasportogasolioself;
    private double trasportogasolioservito;
    private double trasportobenzinaself;
    private double trasportobenzinaservito;
    private double trasportosupremeself;
    private double trasportosupremeservito;
    private double trasportogpl;

    private double altrocostogasolioservito;
    private double altrocostogasolioself;
    private double altrocostobenzinaself;
    private double altrocostobenzinaservito;
    private double altrocostosupremeservito;
    private double altrocostosupremeself;
    private double altrocostogpl;




    @OneToOne
    private QuotazioneGiornaliera quotazionefornitore;

    @OneToOne
    private Fornitore fornitore;

    public long getId() {
        return id;
    }


    public Date getData() {
        return data;
    }

    public double getPrezzosupreme() {
        return prezzosupreme;
    }

    public double getPrezzogasolio() {
        return prezzogasolio;
    }

    public double getPrezzobenzina() {
        return prezzobenzina;
    }

    public double getPrezzogpl() {
        return prezzogpl;
    }

    public double getPrezzobenzinaself() {
        return prezzobenzinaself;
    }

    public double getPrezzogasolioself() {
        return prezzogasolioself;
    }

    public double getPrezzogplself() {
        return prezzogplself;
    }

    public double getPrezzosupremeself() {
        return prezzosupremeself;
    }

    public double getMarginecessionesupreme() {
        return marginecessionesupreme;
    }

    public double getMarginecessionegasolio() {
        return marginecessionegasolio;
    }

    public double getMarginecessionegpl() {
        return marginecessionegpl;
    }

    public double getMarginecessionebenzina() {
        return marginecessionebenzina;
    }

    public double getVolumebenzinaself() {
        return volumebenzinaself;
    }

    public double getVolumebenzinaservito() {
        return volumebenzinaservito;
    }

    public double getVolumegasolioself() {
        return volumegasolioself;
    }

    public double getVolumegasolioservito() {
        return volumegasolioservito;
    }

    public double getAltrocostobenzinaself() {
        return altrocostobenzinaself;
    }

    public double getAltrocostobenzinaservito() {
        return altrocostobenzinaservito;
    }

    public double getAltrocostogasolioself() {
        return altrocostogasolioself;
    }

    public double getAltrocostogasolioservito() {
        return altrocostogasolioservito;
    }

    public double getAltrocostogpl() {
        return altrocostogpl;
    }

    public double getVolumegpl() {
        return volumegpl;
    }

    public double getAltrocostosupremeself() {
        return altrocostosupremeself;
    }

    public double getAltrocostosupremeservito() {
        return altrocostosupremeservito;
    }

    public double getTrasportobenzinaself() {
        return trasportobenzinaself;
    }

    public double getTrasportobenzinaservito() {
        return trasportobenzinaservito;
    }

    public double getTrasportogasolioself() {
        return trasportogasolioself;
    }

    public double getTrasportogasolioservito() {
        return trasportogasolioservito;
    }

    public double getTrasportogpl() {
        return trasportogpl;
    }

    public double getTrasportosupremeself() {
        return trasportosupremeself;
    }

    public double getTrasportosupremeservito() {
        return trasportosupremeservito;
    }

    public double getVolumesupremeself() {
        return volumesupremeself;
    }

    public double getVolumesupremeservito() {
        return volumesupremeservito;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setPrezzosupreme(double prezzosupreme) {
        this.prezzosupreme = prezzosupreme;
    }

    public void setPrezzogpl(double prezzogpl) {
        this.prezzogpl = prezzogpl;
    }

    public void setPrezzogasolio(double prezzogasolio) {
        this.prezzogasolio = prezzogasolio;
    }

    public void setPrezzobenzina(double prezzobenzina) {
        this.prezzobenzina = prezzobenzina;
    }

    public void setPrezzobenzinaself(double prezzobenzinaself) {
        this.prezzobenzinaself = prezzobenzinaself;
    }

    public void setPrezzogasolioself(double prezzogasolioself) {
        this.prezzogasolioself = prezzogasolioself;
    }

    public void setPrezzosupremeself(double prezzosupremeself) {
        this.prezzosupremeself = prezzosupremeself;
    }

    public void setPrezzogplself(double prezzogplself) {
        this.prezzogplself = prezzogplself;
    }

    public void setMarginecessionesupreme(double marginecessionesupreme) {
        this.marginecessionesupreme = marginecessionesupreme;
    }

    public void setMarginecessionegasolio(double marginecessionegasolio) {
        this.marginecessionegasolio = marginecessionegasolio;
    }

    public void setMarginecessionegpl(double marginecessionegpl) {
        this.marginecessionegpl = marginecessionegpl;
    }

    public void setMarginecessionebenzina(double marginecessionebenzina) {
        this.marginecessionebenzina = marginecessionebenzina;
    }

    public void setVolumegasolioself(double volumegasolioself) {
        this.volumegasolioself = volumegasolioself;
    }

    public void setVolumebenzinaself(double volumebenzinaself) {
        this.volumebenzinaself = volumebenzinaself;
    }

    public void setAltrocostobenzinaself(double altrocostobenzinaself) {
        this.altrocostobenzinaself = altrocostobenzinaself;
    }

    public void setVolumebenzinaservito(double volumebenzinaservito) {
        this.volumebenzinaservito = volumebenzinaservito;
    }

    public void setAltrocostobenzinaservito(double altrocostobenzinaservito) {
        this.altrocostobenzinaservito = altrocostobenzinaservito;
    }

    public void setVolumegasolioservito(double volumegasolioservito) {
        this.volumegasolioservito = volumegasolioservito;
    }

    public void setAltrocostogasolioself(double altrocostogasolioself) {
        this.altrocostogasolioself = altrocostogasolioself;
    }

    public void setVolumesupremeself(double volumesupremeself) {
        this.volumesupremeself = volumesupremeself;
    }

    public void setAltrocostogasolioservito(double altrocostogasolioservito) {
        this.altrocostogasolioservito = altrocostogasolioservito;
    }

    public void setAltrocostogpl(double altrocostogpl) {
        this.altrocostogpl = altrocostogpl;
    }

    public void setTrasportobenzinaself(double trasportobenzinaself) {
        this.trasportobenzinaself = trasportobenzinaself;
    }

    public void setAltrocostosupremeself(double altrocostosupremeself) {
        this.altrocostosupremeself = altrocostosupremeself;
    }

    public void setAltrocostosupremeservito(double altrocostosupremeservito) {
        this.altrocostosupremeservito = altrocostosupremeservito;
    }

    public void setTrasportobenzinaservito(double trasportobenzinaservito) {
        this.trasportobenzinaservito = trasportobenzinaservito;
    }

    public void setTrasportogasolioself(double trasportogasolioself) {
        this.trasportogasolioself = trasportogasolioself;
    }

    public void setTrasportogasolioservito(double trasportogasolioservito) {
        this.trasportogasolioservito = trasportogasolioservito;
    }

    public void setTrasportogpl(double trasportogpl) {
        this.trasportogpl = trasportogpl;
    }

    public void setTrasportosupremeself(double trasportosupremeself) {
        this.trasportosupremeself = trasportosupremeself;
    }

    public void setTrasportosupremeservito(double trasportosupremeservito) {
        this.trasportosupremeservito = trasportosupremeservito;
    }

    public void setVolumesupremeservito(double volumesupremeservito) {
        this.volumesupremeservito = volumesupremeservito;
    }

    public void setVolumegpl(double volumegpl) {
        this.volumegpl = volumegpl;
    }


    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!(obj instanceof QuotazioneGiornalieraPuntoVendita)) return false;
        QuotazioneGiornalieraPuntoVendita other = (QuotazioneGiornalieraPuntoVendita) obj;
        return this.id == other.getId();
    }

}
