package com.petroli.gestionefasipetroli.controllers;

import com.petroli.gestionefasipetroli.entities.Fornitore;
import com.petroli.gestionefasipetroli.entities.QuotazioneGiornaliera;
import com.petroli.gestionefasipetroli.services.FornitoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/fornitori")
public class FornitoreController {

    @Autowired
    private FornitoreService fornitoreService;


    @GetMapping("getall")
    public List<Fornitore> getallfornitori(){
        return fornitoreService.tuttifornitori();
    }

    @GetMapping("getfornitore/{nomefornitore}")
    public Fornitore prendifornitore(@PathVariable String nomefornitore){
        return fornitoreService.getfornitore(nomefornitore);
    }

    @PostMapping("aggiungi")
    public List<Fornitore> aggiungifornitore(@RequestBody Fornitore fornitore){
        boolean result = fornitoreService.aggiungifornitore(fornitore);
        return getallfornitori();
    }

    @GetMapping("delete/{idfornitore}")
    public List<Fornitore> eliminafornitore(@PathVariable long idfornitore){
        boolean result = fornitoreService.rimuovifornitore(idfornitore);
        return getallfornitori();
    }

    @PostMapping("eliminaquotazione")
    public boolean eliminaquotazione(@RequestBody QuotazioneGiornaliera quotazioneGiornaliera){
        return fornitoreService.rimuoviquotazione(quotazioneGiornaliera);
    }



}
