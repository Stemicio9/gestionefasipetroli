package com.petroli.gestionefasipetroli.services;

import com.petroli.gestionefasipetroli.dto.DateRange;
import com.petroli.gestionefasipetroli.entities.Fabbisogno;
import com.petroli.gestionefasipetroli.repositories.FabbisognoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FabbisognoService {

    @Autowired
    private FabbisognoRepository fabbisognoRepository;




    public List<Fabbisogno> getallfabbisogni(){
        return fabbisognoRepository.findAllBySmaltitoFalse();
    }

    public Fabbisogno getfabbisognodaid(long id){
        try {
            return fabbisognoRepository.findById(id).get();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Fabbisogno> aggiungifabbisogno(Fabbisogno fabbisogno){
        try{
            fabbisognoRepository.save(fabbisogno);
        }catch(Exception e){
            e.printStackTrace();
        }
        return getallfabbisogni();
    }


    public List<Fabbisogno> eliminafabbisogno(Fabbisogno fabbisogno){
        try {
            fabbisognoRepository.delete(fabbisogno);
        }catch(Exception e){
            e.printStackTrace();
        }
        return getallfabbisogni();
    }


    public List<Fabbisogno> getindaterange(DateRange dateRange){
        return fabbisognoRepository.findAllByDataBetween(dateRange.getData1(),dateRange.getData2());
    }


}
