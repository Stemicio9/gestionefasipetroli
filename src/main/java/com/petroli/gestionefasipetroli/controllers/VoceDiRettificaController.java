package com.petroli.gestionefasipetroli.controllers;

import com.petroli.gestionefasipetroli.entities.VoceDiRettifica;
import com.petroli.gestionefasipetroli.services.VoceDiRettificaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/vocidirettifica")
public class VoceDiRettificaController {

    @Autowired
    private VoceDiRettificaService voceDiRettificaService;


    @GetMapping("getall")
    public List<VoceDiRettifica> getallvocidirettifica(){
        return voceDiRettificaService.tuttelevocidirettifica();
    }

    @GetMapping("getvocedirettifica/{nomevoce}")
    public VoceDiRettifica prendivocedirettifica(@PathVariable String nomevoce){
        return voceDiRettificaService.getvocedirettifica(nomevoce);
    }

    @PostMapping("aggiungi")
    public List<VoceDiRettifica> aggiungivoce(@RequestBody VoceDiRettifica voceDiRettifica){
        boolean result = voceDiRettificaService.aggiungivocedirettifica(voceDiRettifica);
        return getallvocidirettifica();
    }

    @GetMapping("delete/{idvoce}")
    public List<VoceDiRettifica> eliminavocedirettifica(@PathVariable long idvoce){
        boolean result = voceDiRettificaService.rimuovivocedirettifica(idvoce);
        return getallvocidirettifica();
    }



}
