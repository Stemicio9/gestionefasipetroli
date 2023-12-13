package com.petroli.gestionefasipetroli.controllers;

import com.petroli.gestionefasipetroli.dto.DateRange;
import com.petroli.gestionefasipetroli.entities.Concorrente;
import com.petroli.gestionefasipetroli.entities.PrezzoConcorrente;
import com.petroli.gestionefasipetroli.entities.PuntoVendita;
import com.petroli.gestionefasipetroli.repositories.ConcorrenteRepository;
import com.petroli.gestionefasipetroli.services.PrezziConcorrenteService;
import com.petroli.gestionefasipetroli.services.PuntiVenditaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/concorrenti")
public class PrezzoConcorrenteController {

    @Autowired
    private PrezziConcorrenteService prezziConcorrenteService;

    @Autowired
    private PuntiVenditaService puntiVenditaService;

    @Autowired
    private PuntiVenditaController puntiVenditaController;

    @Autowired
    private ConcorrenteRepository concorrenteRepository;


    @PostMapping("aggiungi")
    public boolean aggiungiprezzoconcorrente(@RequestBody PrezzoConcorrente prezzoConcorrente){
        return this.prezziConcorrenteService.aggiungiprezzoconcorrente(prezzoConcorrente);
    }

    @PostMapping("aggiungi/{id}")
    public boolean aggiungiprezzoconcorrenteconid(@RequestBody PrezzoConcorrente prezzoConcorrente,@PathVariable long id){
        PuntoVendita pv = puntiVenditaController.aggiungiconcorrente(prezzoConcorrente.getConcorrente(),id);
        Concorrente c = pv.findconcorrentebyname(prezzoConcorrente.getConcorrente().getNomeconcorrente());
        if(c == null){
            c = new Concorrente();
            c.setNomeconcorrente(prezzoConcorrente.getConcorrente().getNomeconcorrente());
       //     concorrenteRepository.save(c);
        }
        prezzoConcorrente.setConcorrente(c);
        return this.prezziConcorrenteService.aggiungiprezzoconcorrente(prezzoConcorrente);
    }

    @PostMapping("rimuovi")
    public boolean rimuoviprezzoconcorrente(@RequestBody PrezzoConcorrente prezzoConcorrente){
        return this.prezziConcorrenteService.rimuoviprezzoconcorrente(prezzoConcorrente);
    }


    @PostMapping("aggiungitutti")
    public boolean aggiungilistaprezzoconcorrente(@RequestBody List<PrezzoConcorrente> prezzoConcorrente){
        return this.prezziConcorrenteService.aggiornalistaprezziconcorrenti(prezzoConcorrente);
    }

    @PostMapping("getprezzididata")
    public List<PrezzoConcorrente> getlistaprezziperdata(@RequestBody Date data){
        return this.prezziConcorrenteService.getallprezzibydata(data);
    }

    @PostMapping("getprezziconcorrentidipuntovendita/{id}")
    public List<PrezzoConcorrente> getprezziconcorrenti(@RequestBody DateRange data, @PathVariable long id){
        return this.puntiVenditaService.getultimiprezzidiconcorrentidipuntovendita(id,data.getData1());
    }

}
