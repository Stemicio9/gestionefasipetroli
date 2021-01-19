package com.petroli.gestionefasipetroli.services;

import com.petroli.gestionefasipetroli.entities.Fornitore;
import com.petroli.gestionefasipetroli.entities.QuotazioneGiornaliera;
import com.petroli.gestionefasipetroli.repositories.FornitoreRepository;
import com.petroli.gestionefasipetroli.repositories.QuotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FornitoreService {

    @Autowired
    private FornitoreRepository fornitoreRepository;


    @Autowired
    private QuotazioneRepository quotazioneRepository;


    public List<Fornitore> tuttifornitori(){
        return fornitoreRepository.findAll();
    }

    public Fornitore getfornitore(String nomefornitore){
        return fornitoreRepository.findByNomefornitore(nomefornitore);
    }

    public Fornitore getfornitoredaid(long id){
        return fornitoreRepository.findByIdfornitore(id);
    }

    public boolean aggiungifornitore(Fornitore fornitore){
        try{
            fornitoreRepository.save(fornitore);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean rimuovifornitore(String fornitore){
        Fornitore darimuovere = getfornitore(fornitore);
        try{
            fornitoreRepository.delete(darimuovere);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean rimuovifornitore(long idfornitore){
        Fornitore darimuovere = getfornitoredaid(idfornitore);
        try{
            fornitoreRepository.delete(darimuovere);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean rimuoviquotazione(QuotazioneGiornaliera quotazioneGiornaliera){
         Fornitore fornitore = fornitoreRepository.findByQuotazioniContains(quotazioneGiornaliera);

         try{
             fornitore.getQuotazioni().remove(quotazioneGiornaliera);
             fornitoreRepository.save(fornitore);
             quotazioneRepository.delete(quotazioneGiornaliera);
             return true;
         }catch(Exception e){
             e.printStackTrace();
             return false;
         }
    }



}
