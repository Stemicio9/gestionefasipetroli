package com.petroli.gestionefasipetroli.services;

import com.petroli.gestionefasipetroli.entities.*;
import com.petroli.gestionefasipetroli.repositories.FabbisognoRepository;
import com.petroli.gestionefasipetroli.repositories.PreventivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreventivoService {

    @Autowired
    private PreventivoRepository preventivoRepository;

    @Autowired
    private FabbisognoRepository fabbisognoRepository;

    @Autowired
    private PuntiVenditaService puntiVenditaService;




    public List<Preventivo> getall(){
        return preventivoRepository.findAll();
    }

    public Preventivo getpreventivofromfabbisogno(Fabbisogno f){
        return preventivoRepository.findByRiferimento(f);
    }

    public Preventivo getpreventivo(Preventivo p){
        return preventivoRepository.findById(p.getId());
    }


    public boolean aggiungimodificapreventivo(Preventivo preventivo){
        try{

            preventivoRepository.save(preventivo);

            preventivo.getRiferimento().setPreventivoesistente(true);

            fabbisognoRepository.save(preventivo.getRiferimento());

            puntiVenditaService.aggiungipuntovendita(preventivo.getRiferimento().getPuntoVendita());

            toglituttelevocidirettifica(preventivo.getRiferimento().getPuntoVendita(),preventivo.getListavocidirettifica());

            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void toglituttelevocidirettifica(PuntoVendita puntovendita, List<VoceDiRettificaConValore> listavoci){
        for(VoceDiRettificaConValore corr : listavoci){
            puntiVenditaService.cancellavocedirettifica(puntovendita,corr.getId());
        }
    }

    public boolean cancellapreventivo(Preventivo preventivo){
        try{
            PuntoVendita punto = preventivo.getRiferimento().getPuntoVendita();
            List<VoceDiRettificaConValore> listavoci = preventivo.getListavocidirettifica();

            preventivo.getRiferimento().setPreventivoesistente(false);
            preventivoRepository.delete(preventivo);

            ripristinavocidirettifica(punto,listavoci);

            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }


    public void ripristinavocidirettifica(PuntoVendita punto, List<VoceDiRettificaConValore> listavoci){
        for(VoceDiRettificaConValore curr : listavoci){
            puntiVenditaService.aggiungivocedirettifica(punto,curr);
        }
    }


    public boolean cancellavocedirettificadapreventivo(long idpreventivo,VoceDiRettificaConValore voceDiRettificaConValore){
        try {
            Preventivo p = preventivoRepository.findById(idpreventivo);
            p.getListavocidirettifica().remove(voceDiRettificaConValore);
            preventivoRepository.save(p);
            PuntoVendita pv = p.getRiferimento().getPuntoVendita();
            puntiVenditaService.aggiungivocedirettifica(pv, voceDiRettificaConValore);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }


}
