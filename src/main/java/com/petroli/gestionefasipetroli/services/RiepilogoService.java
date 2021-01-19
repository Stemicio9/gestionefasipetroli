package com.petroli.gestionefasipetroli.services;

import com.petroli.gestionefasipetroli.dto.DateRange;
import com.petroli.gestionefasipetroli.dto.RiepilogoPerFrontend;
import com.petroli.gestionefasipetroli.entities.Fabbisogno;
import com.petroli.gestionefasipetroli.entities.Preventivo;
import com.petroli.gestionefasipetroli.entities.Riepilogo;
import com.petroli.gestionefasipetroli.entities.Trasporto;
import com.petroli.gestionefasipetroli.repositories.RiepilogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiepilogoService {

    @Autowired
    private FabbisognoService fabbisognoService;

    @Autowired
    private RiepilogoRepository riepilogoRepository;

    @Autowired
    private PreventivoService preventivoService;

    @Autowired
    private TrasportoService trasportoService;


    public List<Riepilogo> getallriepiloghiindaterange(DateRange range){
        List<Fabbisogno> listainrange = fabbisognoService.getindaterange(range);
        creariepiloghimancanti(listainrange);
        return riepilogoRepository.findAllByFabbisogno_DataBetween(range.getData1(),range.getData2());
    }

    public boolean salvariepilogo(RiepilogoPerFrontend riepilogo){
        try {
            if(riepilogo.getDas() == null || riepilogo.getDas().isEmpty()) {
            } else {
                Fabbisogno fabbisogno = riepilogo.getFabbisogno();
                fabbisogno.setSmaltito(true);
                fabbisognoService.aggiungifabbisogno(fabbisogno);
            }
            riepilogoRepository.save(riepilogo.toriepilogo());
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }


    private void creariepiloghimancanti(List<Fabbisogno> trovati){
        for(Fabbisogno curr: trovati){

            if(!curr.isSmaltito()) {
                Riepilogo riep = riepilogoRepository.findByFabbisogno(curr);
                if (riep == null) {
                    creariepilogodafabbisogno(curr);
                }
            }

        }
    }


    private boolean creariepilogodafabbisogno(Fabbisogno fabbisogno){
        Preventivo preventivo = preventivoService.getpreventivofromfabbisogno(fabbisogno);
        Trasporto trasporto = trasportoService.getbyfabbisogno(fabbisogno);
        if(preventivo == null || trasporto == null) return false;
        Riepilogo dacreare = new Riepilogo(fabbisogno,trasporto,preventivo);
        try{
            riepilogoRepository.save(dacreare);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean cancellariepilogodafabbisogno(Fabbisogno fabbisogno){
        Riepilogo riepilogo = riepilogoRepository.findByFabbisogno(fabbisogno);
        return cancellariepilogo(riepilogo);
    }


    public boolean cancellariepilogo(Preventivo preventivo){
        Riepilogo riepilogo = riepilogoRepository.findByPreventivo(preventivo);
        if(riepilogo == null) return false;
        return cancellariepilogo(riepilogo);
    }


    public boolean cancellariepilogo(Riepilogo riepilogo){
        try{
            riepilogoRepository.delete(riepilogo);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }



}
