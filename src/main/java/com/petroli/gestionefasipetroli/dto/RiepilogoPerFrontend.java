package com.petroli.gestionefasipetroli.dto;

import com.petroli.gestionefasipetroli.entities.Fabbisogno;
import com.petroli.gestionefasipetroli.entities.Preventivo;
import com.petroli.gestionefasipetroli.entities.Riepilogo;
import com.petroli.gestionefasipetroli.entities.Trasporto;

import java.util.Date;

public class RiepilogoPerFrontend {


    private long id;

    private Fabbisogno fabbisogno;


    private double caligasolio;
    private double calibenzina;
    private double calisupreme;
    private double caligpl;

    private double ultimoscarico;

    private Trasporto trasporto;

    private Preventivo preventivo;

    private String das;

    private double prezzogasoliofornitore;
    private double prezzosupremefornitore;
    private double prezzobenzinafornitore;
    private double prezzogplfornitore;


    private String numerofatturafornitore;

    // CALCOLABILE
    private double importofatturafornitore;


    private String numerofatturapartenopea;


    // Da preventivo
    private double importofattura;

    private Date databonifico;
    private double importobonifico;


    // Da Preventivo
    private double importopreventivo;

    // CALCOLABILE
    private double residuodaversare;


    private double totalevolumicarburantitradizionali;


    public Trasporto getTrasporto() {
        return trasporto;
    }

    public Preventivo getPreventivo() {
        return preventivo;
    }

    public double getUltimoscarico() {
        return ultimoscarico;
    }

    public double getCalisupreme() {
        return calisupreme;
    }

    public double getCaligpl() {
        return caligpl;
    }

    public double getCaligasolio() {
        return caligasolio;
    }

    public double getCalibenzina() {
        return calibenzina;
    }

    public Fabbisogno getFabbisogno() {
        return fabbisogno;
    }

    public String getDas() {
        return das;
    }

    public long getId() {
        return id;
    }

    public double getPrezzosupremefornitore() {
        return prezzosupremefornitore;
    }

    public String getNumerofatturapartenopea() {
        return numerofatturapartenopea;
    }

    public String getNumerofatturafornitore() {
        return numerofatturafornitore;
    }

    public double getPrezzogasoliofornitore() {
        return prezzogasoliofornitore;
    }

    public double getResiduodaversare() {
        return residuodaversare;
    }

    public double getImportofatturafornitore() {
        return importofatturafornitore;
    }

    public double getPrezzobenzinafornitore() {
        return prezzobenzinafornitore;
    }

    public double getImportopreventivo() {
        return importopreventivo;
    }

    public double getImportofattura() {
        return importofattura;
    }

    public double getImportobonifico() {
        return importobonifico;
    }


    public Date getDatabonifico() {
        return databonifico;
    }

    public double getPrezzogplfornitore() {
        return prezzogplfornitore;
    }

    public double getTotalevolumicarburantitradizionali() {
        return totalevolumicarburantitradizionali;
    }

    public void setTrasporto(Trasporto trasporto) {
        this.trasporto = trasporto;
    }

    public void setPreventivo(Preventivo preventivo) {
        this.preventivo = preventivo;
    }

    public void setUltimoscarico(double ultimoscarico) {
        this.ultimoscarico = ultimoscarico;
    }

    public void setNumerofatturapartenopea(String numerofatturapartenopea) {
        this.numerofatturapartenopea = numerofatturapartenopea;
    }

    public void setPrezzosupremefornitore(double prezzosupremefornitore) {
        this.prezzosupremefornitore = prezzosupremefornitore;
    }

    public void setPrezzogasoliofornitore(double prezzogasoliofornitore) {
        this.prezzogasoliofornitore = prezzogasoliofornitore;
    }

    public void setNumerofatturafornitore(String numerofatturafornitore) {
        this.numerofatturafornitore = numerofatturafornitore;
    }

    public void setImportofattura(double importofattura) {
        this.importofattura = importofattura;
    }

    public void setDas(String das) {
        this.das = das;
    }

    public void setCalisupreme(double calisupreme) {
        this.calisupreme = calisupreme;
    }

    public void setPrezzobenzinafornitore(double prezzobenzinafornitore) {
        this.prezzobenzinafornitore = prezzobenzinafornitore;
    }

    public void setImportofatturafornitore(double importofatturafornitore) {
        this.importofatturafornitore = importofatturafornitore;
    }

    public void setCaligpl(double caligpl) {
        this.caligpl = caligpl;
    }

    public void setCaligasolio(double caligasolio) {
        this.caligasolio = caligasolio;
    }

    public void setCalibenzina(double calibenzina) {
        this.calibenzina = calibenzina;
    }

    public void setFabbisogno(Fabbisogno fabbisogno) {
        this.fabbisogno = fabbisogno;
    }

    public void setImportobonifico(double importobonifico) {
        this.importobonifico = importobonifico;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setResiduodaversare(double residuodaversare) {
        this.residuodaversare = residuodaversare;
    }

    public void setImportopreventivo(double importopreventivo) {
        this.importopreventivo = importopreventivo;
    }

    public void setDatabonifico(Date databonifico) {
        this.databonifico = databonifico;
    }

    public void setPrezzogplfornitore(double prezzogplfornitore) {
        this.prezzogplfornitore = prezzogplfornitore;
    }

    public void setTotalevolumicarburantitradizionali(double totalevolumicarburantitradizionali) {
        this.totalevolumicarburantitradizionali = totalevolumicarburantitradizionali;
    }

    public Riepilogo toriepilogo(){
        Riepilogo result = new Riepilogo();

        result.setId(id);
        result.setFabbisogno(fabbisogno);
        result.setCaligasolio(caligasolio);
        result.setCalibenzina(calibenzina);
        result.setCalisupreme(calisupreme);
        result.setCaligpl(caligpl);

        result.setUltimoscarico(ultimoscarico);
        result.setTrasporto(trasporto);
        result.setPreventivo(preventivo);
        result.setDas(das);

        result.setNumerofatturafornitore(numerofatturafornitore);
        result.setNumerofatturapartenopea(numerofatturapartenopea);
        result.setDatabonifico(databonifico);
        result.setImportobonifico(importobonifico);

        return result;
    }


    @Override
    public String toString() {
        return id + " ";
    }
}
