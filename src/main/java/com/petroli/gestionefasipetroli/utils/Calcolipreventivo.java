package com.petroli.gestionefasipetroli.utils;

import com.petroli.gestionefasipetroli.dto.RiepilogoPerFrontend;
import com.petroli.gestionefasipetroli.entities.*;
import com.petroli.gestionefasipetroli.services.PuntiVenditaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@Component
public class Calcolipreventivo {


    @Autowired
    private PuntiVenditaService puntiVenditaService;



    public QuotazioneGiornaliera getquotazionegiornalieradifornitore(Fornitore fornitore, Date data){
        for(QuotazioneGiornaliera curr : fornitore.getQuotazioni()){
            if(stessogiorno(curr.getData(),data)){
                return curr;
            }
        }
        return null;
    }

    public boolean stessogiorno(Date date1, Date date2) {
        try {
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(date1);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(date2);
            return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
                    && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)
                    && calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH);
        }catch(Exception e){
            return false;
        }
    }


    public RiepilogoPerFrontend trasformariepilogo(Riepilogo riepilogo){
        RiepilogoPerFrontend result = new RiepilogoPerFrontend();

        result.setId(riepilogo.getId());
        result.setFabbisogno(riepilogo.getFabbisogno());
        result.setCaligasolio(riepilogo.getCaligasolio());
        result.setCalibenzina(riepilogo.getCalibenzina());
        result.setCalisupreme(riepilogo.getCalisupreme());
        result.setCaligpl(riepilogo.getCaligpl());

        result.setUltimoscarico(riepilogo.getUltimoscarico());
        result.setTrasporto(riepilogo.getTrasporto());
        result.setPreventivo(riepilogo.getPreventivo());
        result.setDas(riepilogo.getDas());

        result.setNumerofatturafornitore(riepilogo.getNumerofatturafornitore());
        result.setNumerofatturapartenopea(riepilogo.getNumerofatturapartenopea());
        result.setDatabonifico(riepilogo.getDatabonifico());
        result.setImportobonifico(riepilogo.getImportobonifico());

        result.setListabonifici(riepilogo.getListabonifici());
        result.setFiles(riepilogo.getFiles());

        result.setTotalevolumicarburantitradizionali(totalevolumicarburantitradizionali(riepilogo.getFabbisogno()));

        QuotazioneGiornaliera curr = getquotazionegiornalieradifornitore(riepilogo.getFabbisogno().getFornitore(),riepilogo.getFabbisogno().getData());




        if(curr != null) {

            result.setPrezzogasoliofornitore(curr.getPrezzogasolio());
            result.setPrezzobenzinafornitore(curr.getPrezzobenzina());
            result.setPrezzosupremefornitore(curr.getPrezzosupreme());
            result.setPrezzogplfornitore(curr.getPrezzogpl());
        }else{
            result.setPrezzogasoliofornitore(0);
            result.setPrezzobenzinafornitore(0);
            result.setPrezzosupremefornitore(0);
            result.setPrezzogplfornitore(0);
        }

        result.setImportofatturafornitore(getimportofatturafornitore(riepilogo.getFabbisogno()));
        if(riepilogo.getFabbisogno().isPreventivoesistente()) {
            result.setImportofattura(importofattura(riepilogo.getPreventivo()));
            result.setImportopreventivo(importopreventivo(riepilogo.getPreventivo()));


          //  result.setResiduodaversare(residuodaversare(riepilogo.getImportobonifico(),riepilogo.getPreventivo()));
            result.setResiduodaversare(residuodaversareconlista(riepilogo.getListabonifici(),riepilogo.getPreventivo()));

        }
        return result;
    }


    public double totalevolumicarburantitradizionali(Fabbisogno fabbisogno){
        return fabbisogno.getGasolio() + fabbisogno.getBenzina() + fabbisogno.getSupreme();
    }

    public double importopreventivo(Preventivo preventivo){
        return CALCOLA_TOTALE_PREVENTIVO(preventivo);
    }

    public double importofattura(Preventivo preventivo){
        return CALCOLA_IMPORTO_FATTURA(preventivo);
    }

    public double residuodaversare(double importobonifico, Preventivo preventivo){
        return importopreventivo(preventivo)-importobonifico;
    }

    public double residuodaversareconlista(List<Bonifico> listabonifici, Preventivo preventivo){
        double versato = 0;
        for(Bonifico b: listabonifici){
            versato += b.getImportobonifico();
        }
        return importopreventivo(preventivo) - versato;
    }

    public double getimportofatturafornitore(Fabbisogno fabbisogno){
        return CALCOLA_IMPORTO_FATTURA_FORNITORE(fabbisogno.getFornitore(), fabbisogno);
    }


    public double CALCOLA_TOTALE_PREVENTIVO(Preventivo preventivo){
        try {
            PuntoVendita puntoVendita = preventivo.getRiferimento().getPuntoVendita();
            Cliente cliente = puntiVenditaService.cercaproprietario(puntoVendita);
            double preventivoprimadellevocidirettifica = calcolatotalepreventivo(prendirighepreventivo(preventivo, cliente));
            preventivoprimadellevocidirettifica = arrotondavalore(preventivoprimadellevocidirettifica);
            return toglivocidirettifica(preventivo,preventivoprimadellevocidirettifica);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


    public double CALCOLA_IMPORTO_FATTURA(Preventivo preventivo){
        try {
            PuntoVendita puntoVendita = preventivo.getRiferimento().getPuntoVendita();
            Cliente cliente = puntiVenditaService.cercaproprietario(puntoVendita);
            double preventivoprimadellevocidirettifica = calcolatotalepreventivo(prendirighepreventivo(preventivo, cliente));
            preventivoprimadellevocidirettifica = arrotondavalore(preventivoprimadellevocidirettifica);
            return preventivoprimadellevocidirettifica;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    private double toglivocidirettifica(Preventivo preventivo, double totaleattuale){
        double totaleapagare = totaleattuale;
        for(VoceDiRettificaConValore voce : preventivo.getListavocidirettifica()){
            if(voce.isSegno()){
                totaleapagare += voce.getValore();
            }else{
                totaleapagare -= voce.getValore();
            }
        }
        return arrotondavalore(totaleapagare);
    }


    public double CALCOLA_IMPORTO_FATTURA_FORNITORE(Fornitore fornitore, Fabbisogno fabbisogno){
        double totale = 0;

        Date data = fabbisogno.getData();

        QuotazioneGiornaliera curr = getquotazionegiornalieradifornitore(fornitore,data);

        if(curr == null) return 0;

        if(fabbisogno.getGasolio() > 0){
            totale += fabbisogno.getGasolio()*curr.getPrezzogasolio();
        }

        if(fabbisogno.getBenzina() > 0){
            totale += fabbisogno.getBenzina()*curr.getPrezzobenzina();
        }

        if(fabbisogno.getSupreme() > 0){
            totale += fabbisogno.getSupreme()*curr.getPrezzosupreme();
        }

        if(fabbisogno.getGpl() > 0){
            totale += fabbisogno.getGpl()*curr.getPrezzogpl();
        }

        return totale;
    }


    public List<RigaPerCalcolo> prendirighepreventivo(Preventivo preventivo, Cliente cliente){
        List<RigaPerCalcolo> listarighe = new LinkedList<>();
        RigaPerCalcolo riga = new RigaPerCalcolo();
        double prezzolitro;

        if(preventivo.getRiferimento().getGasolio() > 0){
            if(preventivo.getRiferimento().getPuntoVendita().isAllservito()){
                prezzolitro = calcolaprezzodivendita(preventivo.getPrezzoalpubblicogasolioservito(), cliente.getMarginegasolioservito(), preventivo.getMarginecessionegasolio());
            }else{
                prezzolitro = calcolaprezzodivendita(preventivo.getPrezzoalpubblicogasolioself(), cliente.getMarginegasolioself(), preventivo.getMarginecessionegasolio());
            }
            prezzolitro = arrotondaallaterzacifra(prezzolitro);
            riga.prezzolitro = arrotondaallaterzacifra(prezzolitro);
            riga.qta = preventivo.getRiferimento().getGasolio();
            riga.valore = arrotondavalore(prezzolitro * riga.qta);
            riga.totale = arrotondavalore(riga.valore * 1.22);
            listarighe.add(copiariga(riga));
            riga = new RigaPerCalcolo();
        }

        if(preventivo.getRiferimento().getBenzina() > 0){
            if(preventivo.getRiferimento().getPuntoVendita().isAllservito()){
                prezzolitro = calcolaprezzodivendita(preventivo.getPrezzoalpubblicobenzinaservito(), cliente.getMarginebenzinaservito(), preventivo.getMarginecessionebenzina());
            }else{
                prezzolitro = calcolaprezzodivendita(preventivo.getPrezzoalpubblicobenzinaself(), cliente.getMarginebenzinaself(), preventivo.getMarginecessionebenzina());
            }
            prezzolitro = arrotondaallaterzacifra(prezzolitro);
            riga.prezzolitro = arrotondaallaterzacifra(prezzolitro);
            riga.qta = preventivo.getRiferimento().getBenzina();
            riga.valore = arrotondavalore(prezzolitro * riga.qta);
            riga.totale = arrotondavalore(riga.valore * 1.22);
            listarighe.add(copiariga(riga));
            riga = new RigaPerCalcolo();
        }

        if(preventivo.getRiferimento().getSupreme() > 0){
            if(preventivo.getRiferimento().getPuntoVendita().isAllservito()){
                prezzolitro = calcolaprezzodivendita(preventivo.getPrezzoalpubblicosupremeservito(), cliente.getMarginesupremeservito(), preventivo.getMarginecessionesupreme());
            }else{
                prezzolitro = calcolaprezzodivendita(preventivo.getPrezzoalpubblicosupremeself(), cliente.getMarginesupremeself(), preventivo.getMarginecessionesupreme());
            }
            prezzolitro = arrotondaallaterzacifra(prezzolitro);
            riga.prezzolitro = arrotondaallaterzacifra(prezzolitro);
            riga.qta = preventivo.getRiferimento().getSupreme();
            riga.valore = arrotondavalore(prezzolitro * riga.qta);
            riga.totale = arrotondavalore(riga.valore * 1.22);
            listarighe.add(copiariga(riga));
            riga = new RigaPerCalcolo();
        }

        if(preventivo.getRiferimento().getGpl() > 0){
            prezzolitro = calcolaprezzodivendita(preventivo.getPrezzoalpubblicogplservito(), cliente.getMarginegplservito(), preventivo.getMarginecessionegpl());
            prezzolitro = arrotondaallaterzacifra(prezzolitro);
            riga.prezzolitro = arrotondaallaterzacifra(prezzolitro);
            riga.qta = preventivo.getRiferimento().getGpl();
            riga.valore = arrotondavalore(prezzolitro * riga.qta);
            riga.totale = arrotondavalore(riga.valore * 1.22);
            listarighe.add(copiariga(riga));
        }


        return listarighe;
    }


    private double calcolatotalepreventivo(List<RigaPerCalcolo> listarighe){
        double totale = 0;
        for(RigaPerCalcolo riga : listarighe){
            totale += riga.totale;
        }
        return totale;
    }

    private double calcolaprezzodivendita(double prezzoconiva, double margine, double percentuale){
        return calcolaprezzosenzaiva(prezzoconiva) - (margine*percentuale/100);
    }

    private double calcolaprezzosenzaiva(double prezzo){
        return prezzo/1.22;
    }


    private double arrotondaallaterzacifra(double x){
        return (double)Math.round(x*1000)/1000;
    }

    private double arrotondavalore(double x){
        return (double)Math.round(x*100)/100;
    }

    private RigaPerCalcolo copiariga(RigaPerCalcolo riga){
        RigaPerCalcolo result = new RigaPerCalcolo();
        result.totale = riga.totale;
        result.prezzolitro = riga.prezzolitro;
        result.qta = riga.qta;
        result.prezzototale = riga.prezzototale;
        result.valore = riga.valore;
        return result;
    }

    class RigaPerCalcolo {
        private double qta;
        private double prezzolitro;
        private double prezzototale;
        private double totale;
        private double valore;
    }

}
