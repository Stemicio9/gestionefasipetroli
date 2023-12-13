package com.petroli.gestionefasipetroli.services;


import com.petroli.gestionefasipetroli.entities.*;
import com.petroli.gestionefasipetroli.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class PuntiVenditaService {

    @Autowired
    private PuntiVenditaRepository puntiVenditaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private VoceDiRettificaService voceDiRettificaService;

    @Autowired
    private VoceDiRettificaConValoreRepository voceDiRettificaConValoreRepository;

    @Autowired
    private QutoazioneGiornalieraPuntoVenditaRepository qutoazioneGiornalieraPuntoVenditaRepository;

    @Autowired
    private ConcorrenteRepository concorrenteRepository;

    @Autowired
    private PrezzoConcorrenteRepository prezzoConcorrenteRepository;

    public PuntoVendita getpuntovenditadaid(long id){
        return puntiVenditaRepository.findByIdpunto(id);
    }

    public boolean aggiungipuntovendita(PuntoVendita puntoVendita){
        try{
            puntiVenditaRepository.saveAndFlush(puntoVendita);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public List<PuntoVendita> getallpuntivendita(){
        return puntiVenditaRepository.findAll();
    }


    public boolean deletepuntovendita(long idpuntovendita){
        PuntoVendita daeliminare = getpuntovenditadaid(idpuntovendita);
        return deletepuntovendita(daeliminare);
    }

    public boolean deletepuntovendita(PuntoVendita puntoVendita){
        try {

            eliminatuttelevocidirettificadiunpuntovendita(puntoVendita);
              puntoVendita.getListavocidirettifica().clear();


            Cliente c = cercaproprietario(puntoVendita);
            if(c != null) {
                c.getListapuntivendita().remove(puntoVendita);
                clienteRepository.save(c);
            }


            puntiVenditaRepository.delete(puntoVendita);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }


    public void eliminatuttelevocidirettificadiunpuntovendita(PuntoVendita p){
        for(VoceDiRettificaConValore voce : p.getListavocidirettifica()){
            voceDiRettificaConValoreRepository.delete(voce);
        }
    }


    public Cliente cercaproprietario(PuntoVendita puntovendita){
        return clienteRepository.findByListapuntivenditaContains(puntovendita);
    }


    public PuntoVendita cancellavocedirettifica(PuntoVendita puntoVendita, long idvocedirettifica){
        for(VoceDiRettificaConValore voce : puntoVendita.getListavocidirettifica()){
            if(voce.getId() == idvocedirettifica){
                puntoVendita.getListavocidirettifica().remove(voce);
                break;
            }
        }
        try {
            puntiVenditaRepository.save(puntoVendita);
        }catch(Exception e){
            e.printStackTrace();
        }
        return puntoVendita;
    }


    public PuntoVendita aggiungivocedirettifica(long idpuntovendita, VoceDiRettificaConValore voceDiRettificaConValore){
        PuntoVendita puntovendita = puntiVenditaRepository.findByIdpunto(idpuntovendita);
        return aggiungivocedirettifica(puntovendita,voceDiRettificaConValore);
    }

    public PuntoVendita aggiungivocedirettifica(PuntoVendita puntoVendita, VoceDiRettificaConValore voceDiRettificaConValore){
        try{
            puntoVendita.getListavocidirettifica().add(voceDiRettificaConValore);
            puntiVenditaRepository.save(puntoVendita);
        }catch(Exception e){
            e.printStackTrace();
        }
        return puntoVendita;
    }

    public boolean aggiungiquotazione(QuotazioneGiornalieraPuntoVendita quotazioneGiornalieraPuntoVendita, long id){
        PuntoVendita puntovendita = puntiVenditaRepository.findByIdpunto(id);
        try{
           // QuotazioneGiornalieraPuntoVendita conid = qutoazioneGiornalieraPuntoVenditaRepository.save(quotazioneGiornalieraPuntoVendita);
            puntovendita.getQuotazioni().add(quotazioneGiornalieraPuntoVendita);
            puntiVenditaRepository.save(puntovendita);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public long rimuoviquotazione(QuotazioneGiornalieraPuntoVendita quotazioneGiornalieraPuntoVendita){
        PuntoVendita puntovendita = puntiVenditaRepository.findByQuotazioniContains(quotazioneGiornalieraPuntoVendita);

        try{
            puntovendita.getQuotazioni().remove(quotazioneGiornalieraPuntoVendita);
            puntiVenditaRepository.save(puntovendita);
            qutoazioneGiornalieraPuntoVenditaRepository.delete(quotazioneGiornalieraPuntoVendita);
            return puntovendita.getIdpunto();
        }catch(Exception e){
            e.printStackTrace();
            return puntovendita.getIdpunto();
        }
    }


    public List<PrezzoConcorrente> getultimiprezzidiconcorrentidipuntovendita(long pv, Date olddata){
        List<PrezzoConcorrente> result = new LinkedList<>();
        PuntoVendita puntoVendita = this.puntiVenditaRepository.findByIdpunto(pv);
        List<Concorrente> lista = puntoVendita.getListaconcorrenti();
        System.out.println("LA LISTA DEI CONCORRENTI");
        System.out.println(lista);

        Date data = addDays(olddata,1);

        for(Concorrente curr : lista){
            List<PrezzoConcorrente> max = prezzoConcorrenteRepository.findAllByConcorrente_IdAndDataBeforeOrderByDataDesc(curr.getId(),data);
            System.out.println("NUMERO DI PREZZI TROVATI PER " + curr.getNomeconcorrente());
            System.out.println(max.size());
            if(max.size() > 0) {
                System.out.println("ULTIMO PREZZO TROVATO");
                System.out.println(max.get(0));
                result.add(max.get(0));
            }
        }
        return result;
    }

    public boolean aggiungiconcorrente(Concorrente concorrente, long id){
        PuntoVendita puntovendita = puntiVenditaRepository.findByIdpunto(id);
        try{
            puntovendita.getListaconcorrenti().add(concorrente);
            puntiVenditaRepository.save(puntovendita);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean rimuoviconcorrente(Concorrente concorrente, long id){
        PuntoVendita puntovendita = puntiVenditaRepository.findByIdpunto(id);
        try{
            puntovendita.getListaconcorrenti().remove(concorrente);
            puntiVenditaRepository.save(puntovendita);
            concorrenteRepository.delete(concorrente);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }


    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

}
