package com.petroli.gestionefasipetroli.controllers;

import com.petroli.gestionefasipetroli.dto.DateRange;
import com.petroli.gestionefasipetroli.entities.Cliente;
import com.petroli.gestionefasipetroli.entities.Fabbisogno;
import com.petroli.gestionefasipetroli.entities.Preventivo;
import com.petroli.gestionefasipetroli.entities.Trasporto;
import com.petroli.gestionefasipetroli.services.FabbisognoService;
import com.petroli.gestionefasipetroli.services.PreventivoService;
import com.petroli.gestionefasipetroli.services.RiepilogoService;
import com.petroli.gestionefasipetroli.services.TrasportoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/fabbisogno")
public class FabbisognoController {

    @Autowired
    private FabbisognoService fabbisognoService;

    @Autowired
    private TrasportoService trasportoService;

    @Autowired
    private PreventivoService preventivoService;

    @Autowired
    private RiepilogoService riepilogoService;


    @GetMapping("getall")
    public List<Fabbisogno> getallfabbisogni(){
        return fabbisognoService.getallfabbisogni();
    }

    @PostMapping("getmieifabbisogni")
    public List<Fabbisogno> getmieifabbisogni(@RequestBody Cliente cliente){
        return fabbisognoService.getmieifabbisogni(cliente);
    }

    @PostMapping("getallindaterange")
    public List<Fabbisogno> getallfabbisogniindaterange(@RequestBody DateRange range) {
        return fabbisognoService.getindaterange(range);
    }

    @GetMapping("getfabbisogno/{id}")
    public Fabbisogno getfabbisogno(@RequestBody long id){
        return fabbisognoService.getfabbisognodaid(id);
    }

    @PostMapping("aggiungi")
    public List<Fabbisogno> aggiungifabbisogno(@RequestBody Fabbisogno fabbisogno){
        List<Fabbisogno> result =  fabbisognoService.aggiungifabbisogno(fabbisogno);
        return result;
    }

    @PostMapping("aggiungilatocliente")
    public List<Fabbisogno> aggiungifabbisognolatocliente(@RequestBody Fabbisogno fabbisogno){
        List<Fabbisogno> result =  fabbisognoService.aggiungifabbisognolatocliente(fabbisogno);
        return result;
    }

    @PostMapping("elimina")
    public List<Fabbisogno> eliminafabbisogno(@RequestBody Fabbisogno fabbisogno){

        try{
            Trasporto t = trasportoService.getbyfabbisognoid(fabbisogno.getId());
            t.setFabbisogno(null);
            trasportoService.salva(t);
            trasportoService.rimuovi(t);
        }catch(Exception e){
            e.printStackTrace();
        }


        try {
            Preventivo preventivo = preventivoService.getpreventivofromfabbisogno(fabbisogno);
            preventivo.setRiferimento(null);
            preventivoService.aggiungimodificapreventivo(preventivo);
            preventivoService.cancellapreventivo(preventivo);
        } catch(Exception e){
            e.printStackTrace();
        }

        try{
            riepilogoService.cancellariepilogodafabbisogno(fabbisogno);
        }catch(Exception e){
            e.printStackTrace();
        }



        return fabbisognoService.eliminafabbisogno(fabbisogno);
    }

}
