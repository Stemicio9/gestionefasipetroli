package com.petroli.gestionefasipetroli.controllers;

import com.petroli.gestionefasipetroli.entities.Fabbisogno;
import com.petroli.gestionefasipetroli.entities.Preventivo;
import com.petroli.gestionefasipetroli.entities.VoceDiRettificaConValore;
import com.petroli.gestionefasipetroli.services.PreventivoService;
import com.petroli.gestionefasipetroli.services.RiepilogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/preventivo")
public class PreventivoController {


    @Autowired
    private PreventivoService preventivoService;


    @Autowired
    private RiepilogoService riepilogoService;


    @PostMapping("getpreventivodifabbisogno")
    public Preventivo getpreventivodifabbisogno(@RequestBody Fabbisogno fabbisogno){
        return preventivoService.getpreventivofromfabbisogno(fabbisogno);
    }

    @PostMapping("aggiungimodifica")
    public boolean aggiungimodificapreventivo(@RequestBody Preventivo preventivo){
        return preventivoService.aggiungimodificapreventivo(preventivo);
    }


    @PostMapping("cancella")
    public boolean cancellapreventivo(@RequestBody Preventivo preventivo){
        boolean res = riepilogoService.cancellariepilogo(preventivo);
     //   if(!res) return false;
        return preventivoService.cancellapreventivo(preventivo);
    }

    @PostMapping("cancelladafabbisogno")
    public boolean cancellapreventivodafabbisogno(@RequestBody Fabbisogno fabbisogno){
        Preventivo dacancellare = getpreventivodifabbisogno(fabbisogno);
        return cancellapreventivo(dacancellare);
    }


    @PostMapping("cancellavocedirettificadapreventivo/{idpreventivo}")
    public boolean cancellavocedirettificadapreventivo(@RequestBody VoceDiRettificaConValore voceDiRettificaConValore,@PathVariable long idpreventivo){
        return preventivoService.cancellavocedirettificadapreventivo(idpreventivo,voceDiRettificaConValore);
    }




}
