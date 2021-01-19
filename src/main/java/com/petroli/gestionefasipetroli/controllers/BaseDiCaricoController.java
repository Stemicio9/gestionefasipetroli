package com.petroli.gestionefasipetroli.controllers;

import com.petroli.gestionefasipetroli.entities.BaseDiCarico;
import com.petroli.gestionefasipetroli.services.BaseDiCaricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/basidicarico")
public class BaseDiCaricoController {

    @Autowired
    private BaseDiCaricoService baseDiCaricoService;



    @GetMapping("getall")
    public List<BaseDiCarico> getallbasidicarico(){
        return baseDiCaricoService.tuttelebasidicarico();
    }

    @GetMapping("getbasedicarico/{nomebase}")
    public BaseDiCarico prendibasedicarico(@PathVariable String nomebase){
        return baseDiCaricoService.getbasedicarico(nomebase);
    }

    @PostMapping("aggiungi")
    public List<BaseDiCarico> aggiungibasedicarico(@RequestBody BaseDiCarico basedicarico){
        boolean result = baseDiCaricoService.aggiungibasedicarico(basedicarico);
        return getallbasidicarico();
    }

    @GetMapping("delete/{idbase}")
    public List<BaseDiCarico> eliminabasedicarico(@PathVariable long idbase){
        boolean result = baseDiCaricoService.rimuovibasedicarico(idbase);
        return getallbasidicarico();
    }



}
