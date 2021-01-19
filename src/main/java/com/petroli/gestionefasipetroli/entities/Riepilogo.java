package com.petroli.gestionefasipetroli.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;


@Entity
public class Riepilogo {


    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    private Fabbisogno fabbisogno;


    private double caligasolio;
    private double calibenzina;
    private double calisupreme;
    private double caligpl;

    private double ultimoscarico;


    @OneToOne
    private Trasporto trasporto;

    @OneToOne
    private Preventivo preventivo;

    private String das;

    private String numerofatturafornitore;

    private String numerofatturapartenopea;

    private Date databonifico;
    private double importobonifico;



    public Riepilogo(){}

    public Riepilogo(Fabbisogno fabbisogno,Trasporto trasporto,Preventivo preventivo){
        this.fabbisogno = fabbisogno;
        this.trasporto = trasporto;
        this.preventivo = preventivo;
    }


    public long getId() {
        return id;
    }


    public Fabbisogno getFabbisogno() {
        return fabbisogno;
    }

    public double getCalibenzina() {
        return calibenzina;
    }

    public double getCaligasolio() {
        return caligasolio;
    }

    public double getCaligpl() {
        return caligpl;
    }

    public double getCalisupreme() {
        return calisupreme;
    }

    public double getUltimoscarico() {
        return ultimoscarico;
    }

    public String getDas() {
        return das;
    }

    public Date getDatabonifico() {
        return databonifico;
    }

    public double getImportobonifico() {
        return importobonifico;
    }

    public String getNumerofatturafornitore() {
        return numerofatturafornitore;
    }

    public String getNumerofatturapartenopea() {
        return numerofatturapartenopea;
    }

    public Preventivo getPreventivo() {
        return preventivo;
    }

    public Trasporto getTrasporto() {
        return trasporto;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFabbisogno(Fabbisogno fabbisogno) {
        this.fabbisogno = fabbisogno;
    }

    public void setCalibenzina(double calibenzina) {
        this.calibenzina = calibenzina;
    }

    public void setCaligasolio(double caligasolio) {
        this.caligasolio = caligasolio;
    }

    public void setCaligpl(double caligpl) {
        this.caligpl = caligpl;
    }

    public void setCalisupreme(double calisupreme) {
        this.calisupreme = calisupreme;
    }

    public void setDas(String das) {
        this.das = das;
    }

    public void setNumerofatturafornitore(String numerofatturafornitore) {
        this.numerofatturafornitore = numerofatturafornitore;
    }

    public void setUltimoscarico(double ultimoscarico) {
        this.ultimoscarico = ultimoscarico;
    }

    public void setDatabonifico(Date databonifico) {
        this.databonifico = databonifico;
    }

    public void setImportobonifico(double importobonifico) {
        this.importobonifico = importobonifico;
    }

    public void setNumerofatturapartenopea(String numerofatturapartenopea) {
        this.numerofatturapartenopea = numerofatturapartenopea;
    }

    public void setPreventivo(Preventivo preventivo) {
        this.preventivo = preventivo;
    }

    public void setTrasporto(Trasporto trasporto) {
        this.trasporto = trasporto;
    }



}
