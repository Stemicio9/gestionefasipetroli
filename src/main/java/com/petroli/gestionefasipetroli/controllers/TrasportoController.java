package com.petroli.gestionefasipetroli.controllers;

import com.petroli.gestionefasipetroli.dto.TrasportoFilter;
import com.petroli.gestionefasipetroli.dto.Viaggio;
import com.petroli.gestionefasipetroli.entities.Fabbisogno;
import com.petroli.gestionefasipetroli.entities.Trasporto;
import com.petroli.gestionefasipetroli.services.TrasportoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/trasporto")
public class TrasportoController {

    @Autowired
    private TrasportoService trasportoService;



    @GetMapping("getall")
    public List<Trasporto> getall(){
        return trasportoService.getalltrasporti();
    }

    @GetMapping("findbyfabbisognoid/{id}")
    public Trasporto findbyid(@PathVariable long id){
        return trasportoService.getbyfabbisognoid(id);
    }

    @PostMapping("salva")
    public boolean salva(@RequestBody Trasporto trasporto){
       return trasportoService.salva(trasporto);
    }

    @PostMapping("rimuovi")
    public boolean rimuovi(@RequestBody Trasporto trasporto){
        return trasportoService.rimuovi(trasporto);

    }

    @PostMapping("rimuovidafabbisogno")
    public boolean rimuovidafabbisogno(@RequestBody Fabbisogno fabbisogno){
        Trasporto dacancellare = findbyid(fabbisogno.getId());
        return rimuovi(dacancellare);
    }


    @PostMapping("findviaggio")
    public Set<Viaggio> fiindviaggio(@RequestBody TrasportoFilter trasportoFilter){
        Set<Viaggio> result =  trasportoService.findbyfilter(trasportoFilter);
        return result;
    }

}
