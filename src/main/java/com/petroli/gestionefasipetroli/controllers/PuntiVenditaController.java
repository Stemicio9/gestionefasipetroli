package com.petroli.gestionefasipetroli.controllers;


import com.petroli.gestionefasipetroli.dto.PuntoVenditaConCliente;
import com.petroli.gestionefasipetroli.entities.*;
import com.petroli.gestionefasipetroli.services.ClienteService;
import com.petroli.gestionefasipetroli.services.PuntiVenditaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/puntivendita")
public class PuntiVenditaController {


    @Autowired
    private PuntiVenditaService puntiVenditaService;

    @Autowired
    private ClienteService clienteService;



    @GetMapping("getall")
    public List<PuntoVendita> getallpuntivendita(){
        return puntiVenditaService.getallpuntivendita();
    }




    @GetMapping("getpuntovendita/{id}")
    public PuntoVendita getpuntovendita(@PathVariable long id){
        return puntiVenditaService.getpuntovenditadaid(id);
    }


    @PostMapping("aggiungipuntovendita")
    public boolean aggiungipuntovendita(@RequestBody PuntoVendita puntoVendita){
        return puntiVenditaService.aggiungipuntovendita(puntoVendita);
    }


    @GetMapping("eliminapuntovendita/{idpuntovendita}")
    public List<PuntoVenditaConCliente> elimminapuntovendita(@PathVariable long idpuntovendita){
        boolean result =  puntiVenditaService.deletepuntovendita(idpuntovendita);
        return tuttipuntivenditaconcliente();
    }


    @GetMapping("tuttipuntivenditavuoti")
    public List<PuntoVendita> tuttipuntivenditaascavallo(){
        return clienteService.getallpuntivenditasenzacliente();
    }



    @PostMapping("tuttiimieipuntivendita")
    public List<PuntoVenditaConCliente> tuttiimieipuntivendita(@RequestBody Cliente cliente){
       List<PuntoVendita> listapunti =  clienteService.getpuntivenditadiattivita(cliente.getIdcliente());
        List<PuntoVenditaConCliente> result = new LinkedList<>();
        for(PuntoVendita curr : listapunti){
            PuntoVenditaConCliente nuovo = new PuntoVenditaConCliente();
            nuovo.setPuntovendita(curr);
            result.add(nuovo);
        }
        return result;
    }

    @GetMapping("tuttipuntivenditaconcliente")
    public List<PuntoVenditaConCliente> tuttipuntivenditaconcliente(){
        List<PuntoVendita> tuttiquelliascavallo = tuttipuntivenditaascavallo();

        List<Cliente> listaclienti = clienteService.getallclienti();

        List<PuntoVenditaConCliente> result = new LinkedList<>();

        // AGGIUNGO TUTTI I PUNTI VENDITA A SCAVALLO
        for(PuntoVendita scavallo : tuttiquelliascavallo){
            PuntoVenditaConCliente nuovo = new PuntoVenditaConCliente();
            nuovo.setPuntovendita(scavallo);
            result.add(nuovo);
        }

        // AGGIUNGO TUTTI I PUNTI VENDITA CON IL CLIENTE
        for(Cliente c : listaclienti){
            for(PuntoVendita corr : c.getListapuntivendita()){
                PuntoVenditaConCliente nuovo = new PuntoVenditaConCliente();
                nuovo.setPuntovendita(corr);
                nuovo.setNomecliente(c.getNomecliente());
                result.add(nuovo);
            }
        }

        return result;

    }


    @PostMapping("cercaproprietario")
    public Cliente cercaproprietario(@RequestBody PuntoVendita puntoVendita){
        return puntiVenditaService.cercaproprietario(puntoVendita);
    }

    @PostMapping("cancellavocedirettifica/{idvoce}")
    public PuntoVendita cancellavocedirettifica(@RequestBody PuntoVendita puntoVendita,@PathVariable long idvoce){
         return puntiVenditaService.cancellavocedirettifica(puntoVendita,idvoce);
    }

    @PostMapping("aggiungivocedirettifica/{idpunto}")
    public PuntoVendita aggiungivocedirettifica(@RequestBody VoceDiRettificaConValore voceDiRettificaConValore,@PathVariable long idpunto){
        return puntiVenditaService.aggiungivocedirettifica(idpunto,voceDiRettificaConValore);
    }

    @PostMapping("aggiungiquotazione/{id}")
    public PuntoVendita aggiungiquotazione(@RequestBody QuotazioneGiornalieraPuntoVendita quotazioneGiornaliera, @PathVariable long id){
        boolean res =  puntiVenditaService.aggiungiquotazione(quotazioneGiornaliera, id);
        return puntiVenditaService.getpuntovenditadaid(id);
    }

    @PostMapping("eliminaquotazione")
    public PuntoVendita eliminaquotazione(@RequestBody QuotazioneGiornalieraPuntoVendita quotazioneGiornaliera){
      long id = puntiVenditaService.rimuoviquotazione(quotazioneGiornaliera);
      return puntiVenditaService.getpuntovenditadaid(id);
    }

    @PostMapping("aggiungiconcorrente/{id}")
    public PuntoVendita aggiungiconcorrente(@RequestBody Concorrente concorrente, @PathVariable long id){
        boolean res = puntiVenditaService.aggiungiconcorrente(concorrente,id);
        return puntiVenditaService.getpuntovenditadaid(id);
    }

    @PostMapping("eliminaconcorrente/{id}")
    public PuntoVendita eliminaconcorrente(@RequestBody Concorrente concorrente, @PathVariable long id){
        boolean res = puntiVenditaService.rimuoviconcorrente(concorrente,id);
        return puntiVenditaService.getpuntovenditadaid(id);
    }



}
