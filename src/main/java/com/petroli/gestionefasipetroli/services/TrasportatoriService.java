package com.petroli.gestionefasipetroli.services;

import com.petroli.gestionefasipetroli.entities.Atk;
import com.petroli.gestionefasipetroli.entities.Autista;
import com.petroli.gestionefasipetroli.entities.Rimorchio;
import com.petroli.gestionefasipetroli.entities.Trasportatore;
import com.petroli.gestionefasipetroli.repositories.AtkRepository;
import com.petroli.gestionefasipetroli.repositories.AutistaRepository;
import com.petroli.gestionefasipetroli.repositories.RimorchiRepository;
import com.petroli.gestionefasipetroli.repositories.TrasportatoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrasportatoriService {

    @Autowired
    private AtkRepository atkRepository;

    @Autowired
    private AutistaRepository autistaRepository;

    @Autowired
    private RimorchiRepository rimorchiRepository;

    @Autowired
    private TrasportatoreRepository trasportatoreRepository;


    // ********* TRASPORTATORI ***********
    public List<Trasportatore> getalltrasportatori(){
        return trasportatoreRepository.findAll();
    }


    public Trasportatore gettrasportatore(String nometrasportatore){
        return trasportatoreRepository.findByNometrasportatore(nometrasportatore);
    }

    public boolean aggiungitrasportatore(Trasportatore trasportatore){
        try{
            trasportatoreRepository.saveAndFlush(trasportatore);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean rimuovitrasportatore(String idtrasportatore){
        Trasportatore darimuovere = gettrasportatore(idtrasportatore);
        try{
            trasportatoreRepository.delete(darimuovere);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // ******* FINE TRASPORTATORI  **********


    // ******* ATK *********
    public List<Atk> getallatk(){
        return atkRepository.findAll();
    }

    public Atk getatk(long idatk){
        return atkRepository.findByIdatk(idatk);
    }

    public Atk getatkdacodice(String codice){
        return atkRepository.findByCodice(codice);
    }

    public Atk getatkdatarga(String targa){
        return atkRepository.findByTarga(targa);
    }

    public boolean aggiungiatk(Atk atk){
        try{
            atkRepository.saveAndFlush(atk);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean rimuoviatk(long idatk){

        try {

            Atk darimuovere = getatk(idatk);

            Trasportatore trasp = trasportatoreRepository.findByListaatkContains(darimuovere);
            trasp.getListaatk().remove(darimuovere);
            trasportatoreRepository.save(trasp);

            return rimuoviatk(darimuovere);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean rimuoviatkdacodice(String codiceatk){
        Atk darimuovere = getatkdacodice(codiceatk);
        return rimuoviatk(darimuovere);
    }

    public boolean rimuoviatkdatarga(String targa){
        Atk darimuovere = getatkdatarga(targa);
        return rimuoviatk(darimuovere);
    }



    public boolean rimuoviatk(Atk atk){
        try{
            atkRepository.delete(atk);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // ******** FINE ATK **********


    // ******** AUTISTI *********

    public List<Autista> getallautisti(){
        return autistaRepository.findAll();
    }

    public Autista getautista(long idautista){
        return autistaRepository.findByIdautista(idautista);
    }

    public Autista getautista(String nomeautista){
        return autistaRepository.findByNomeautista(nomeautista);
    }

    public boolean aggiungiautista(Autista autista){
        try{
            autistaRepository.saveAndFlush(autista);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean rimuoviautista(long idautista){
        try {
            Autista darimuovere = getautista(idautista);
            Trasportatore trasp = trasportatoreRepository.findByListaautistiContains(darimuovere);
            trasp.getListaautisti().remove(darimuovere);
            trasportatoreRepository.save(trasp);
            return rimuoviautista(darimuovere);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean rimuoviautista(String nomeautista){
        Autista darimuovere = getautista(nomeautista);
        return rimuoviautista(darimuovere);
    }

    public boolean rimuoviautista(Autista autista){
        try{
            autistaRepository.delete(autista);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // ******* FINE AUTISTI ********


    // ******* RIMORCHI ***********

    public List<Rimorchio> getallrimorchi(){
        return rimorchiRepository.findAll();
    }

    public List<Rimorchio> getrimorchiditrasportatore(String idtrasportatore) {
        Trasportatore t = gettrasportatore(idtrasportatore);
        return t.getListarimorchi();
    }



    public Rimorchio getrimorchio(String targa){
        return rimorchiRepository.findByTarga(targa);
    }

    public Rimorchio aggiungirimorchio(Rimorchio rimorchio){
        try{
            Rimorchio result = rimorchiRepository.saveAndFlush(rimorchio);
            return result;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }



    public boolean rimuovirimorchio(String targa){
        try {
            Rimorchio darimuovere = getrimorchio(targa);

            Trasportatore trasp = trasportatoreRepository.findByListarimorchiContains(darimuovere);
            trasp.getListaautisti().remove(darimuovere);
            trasportatoreRepository.save(trasp);

            return rimuovirimorchio(darimuovere);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean rimuovirimorchio(Rimorchio rimorchio){
        try{
            rimorchiRepository.delete(rimorchio);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // ********* FINE RIMORCHI **************


}
