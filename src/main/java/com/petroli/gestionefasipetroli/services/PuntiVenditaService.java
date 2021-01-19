package com.petroli.gestionefasipetroli.services;


import com.petroli.gestionefasipetroli.entities.Cliente;
import com.petroli.gestionefasipetroli.entities.PuntoVendita;
import com.petroli.gestionefasipetroli.entities.VoceDiRettificaConValore;
import com.petroli.gestionefasipetroli.repositories.ClienteRepository;
import com.petroli.gestionefasipetroli.repositories.PuntiVenditaRepository;
import com.petroli.gestionefasipetroli.repositories.VoceDiRettificaConValoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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



}
