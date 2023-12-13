package com.petroli.gestionefasipetroli.services;

import com.petroli.gestionefasipetroli.entities.PrezzoConcorrente;
import com.petroli.gestionefasipetroli.repositories.PrezzoConcorrenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PrezziConcorrenteService {

    @Autowired
    private PrezzoConcorrenteRepository prezzoConcorrenteRepository;


    public boolean aggiungiprezzoconcorrente(PrezzoConcorrente prezzoConcorrente){
        try{
            prezzoConcorrenteRepository.save(prezzoConcorrente);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean rimuoviprezzoconcorrente(PrezzoConcorrente prezzoConcorrente){
        try{
            prezzoConcorrenteRepository.delete(prezzoConcorrente);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean aggiornalistaprezziconcorrenti(List<PrezzoConcorrente> listaprezzi){
        for(PrezzoConcorrente curr : listaprezzi){
            aggiungiprezzoconcorrente(curr);
        }
        return true;
    }

    public List<PrezzoConcorrente> getallprezzibydata(Date data){
        return prezzoConcorrenteRepository.findAllByData(data);
    }


}
