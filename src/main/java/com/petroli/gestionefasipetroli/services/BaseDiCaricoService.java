package com.petroli.gestionefasipetroli.services;

import com.petroli.gestionefasipetroli.entities.BaseDiCarico;
import com.petroli.gestionefasipetroli.repositories.BaseDiCaricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseDiCaricoService {

    @Autowired
    private BaseDiCaricoRepository baseDiCaricoRepository;




    public List<BaseDiCarico> tuttelebasidicarico(){
        return baseDiCaricoRepository.findAll();
    }

    public BaseDiCarico getbasedicarico(String nomebase){
        return baseDiCaricoRepository.findByNomebasedicarico(nomebase);
    }

    public BaseDiCarico getvocedaid(long id){
        return baseDiCaricoRepository.findByIdbasedicarico(id);
    }

    public boolean aggiungibasedicarico(BaseDiCarico baseDiCarico){
        try{
            baseDiCaricoRepository.save(baseDiCarico);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean rimuovibasedicarico(String nomebase){
        BaseDiCarico darimuovere = getbasedicarico(nomebase);
        try{
            baseDiCaricoRepository.delete(darimuovere);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean rimuovibasedicarico(long idbase){
        BaseDiCarico darimuovere = getvocedaid(idbase);
        try{
            baseDiCaricoRepository.delete(darimuovere);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }


}
