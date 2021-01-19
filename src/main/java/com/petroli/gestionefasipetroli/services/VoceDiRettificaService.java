package com.petroli.gestionefasipetroli.services;


import com.petroli.gestionefasipetroli.entities.VoceDiRettifica;
import com.petroli.gestionefasipetroli.repositories.VoceDiRettificaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoceDiRettificaService {

    @Autowired
    private VoceDiRettificaRepository voceDiRettificaRepository;


    public List<VoceDiRettifica> tuttelevocidirettifica(){
        return voceDiRettificaRepository.findAll();
    }

    public VoceDiRettifica getvocedirettifica(String nomevoce){
        return voceDiRettificaRepository.findByNomevoce(nomevoce);
    }

    public VoceDiRettifica getvocedaid(long id){
        return voceDiRettificaRepository.findByIdvocedirettifica(id);
    }

    public boolean aggiungivocedirettifica(VoceDiRettifica voceDiRettifica){
        try{
            voceDiRettificaRepository.save(voceDiRettifica);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean rimuovivocedirettifica(String nomevoce){
        VoceDiRettifica darimuovere = getvocedirettifica(nomevoce);
        try{
            voceDiRettificaRepository.delete(darimuovere);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean rimuovivocedirettifica(long idvoce){
        VoceDiRettifica darimuovere = getvocedaid(idvoce);
        try{
            voceDiRettificaRepository.delete(darimuovere);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }



}
