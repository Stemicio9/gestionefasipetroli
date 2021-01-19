package com.petroli.gestionefasipetroli.controllers;

import com.petroli.gestionefasipetroli.entities.Atk;
import com.petroli.gestionefasipetroli.entities.Autista;
import com.petroli.gestionefasipetroli.entities.Rimorchio;
import com.petroli.gestionefasipetroli.entities.Trasportatore;
import com.petroli.gestionefasipetroli.services.TrasportatoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/trasportatori")
public class TrasportatoriController {


    @Autowired
    private TrasportatoriService trasportatoriService;



    // ********** TRASPORTATORI *************

    @GetMapping("getall")
    private List<Trasportatore> listatrasportatori(){
        return trasportatoriService.getalltrasportatori();
    }

    @GetMapping("gettrasportatore/{idtrasportatore}")
    private Trasportatore gettrasportatore(@PathVariable String idtrasportatore){
        return trasportatoriService.gettrasportatore(idtrasportatore);
    }




    @PostMapping("aggiungitrasportatore")
    public List<Trasportatore> aggiungitrasportatore(@RequestBody Trasportatore trasportatore){

    /*


        for(Rimorchio r : trasportatore.getListarimorchi()){
            aggiungirimorchio(r);
        }


        for(Atk a : trasportatore.getListaatk()){
            aggiungiatk(a);
        }



        for(Autista a : trasportatore.getListaautisti()){
            aggiungiautista(a);
        }

        */





        boolean result = trasportatoriService.aggiungitrasportatore(trasportatore);
        return listatrasportatori();
    }

    @GetMapping("rimuovitrasportatore/{idtrasportatore}")
    public List<Trasportatore> rimuovitrasportatore(@PathVariable String idtrasportatore){
        boolean result = trasportatoriService.rimuovitrasportatore(idtrasportatore);
        return listatrasportatori();
    }

    // *********** FINE TRASPORTATORI *************


    // *********** ATK ***************

    @GetMapping("getallatk")
    public List<Atk> getallatk(){
        return trasportatoriService.getallatk();
    }

    @GetMapping("getatk/{idatk}")
    public Atk getatk(@PathVariable long idatk){
        return trasportatoriService.getatk(idatk);
    }

    @GetMapping("getatkdacodice/{codice}")
    public Atk getatkdacodice(@PathVariable String codice){
        return trasportatoriService.getatkdacodice(codice);
    }

    @GetMapping("getatkdatarga/{targa}")
    public Atk getatkdatarga(@PathVariable String targa){
        return trasportatoriService.getatkdatarga(targa);
    }

    @PostMapping("aggiungiatk")
    public List<Atk> aggiungiatk(@RequestBody Atk atk){
        boolean result = trasportatoriService.aggiungiatk(atk);
        return getallatk();
    }

    @GetMapping("rimuoviatk/{idatk}")
    public List<Atk> rimuoviatk(@PathVariable long idatk){
        // todo
        // staccare l'atk dal trasportatore

        boolean result = trasportatoriService.rimuoviatk(idatk);
        return getallatk();
    }

    // ******** FINE ATK **********

    @GetMapping("getallrimorchi")
    public List<Rimorchio> getallrimorchi(){
        return trasportatoriService.getallrimorchi();
    }



    @GetMapping("getrimorchiodatarga/{targa}")
    public Rimorchio getrimorchiodatarga(@PathVariable String targa){
        return trasportatoriService.getrimorchio(targa);
    }

    @PostMapping("aggiungirimorchio")
    public Rimorchio aggiungirimorchio(@RequestBody Rimorchio rimorchio){
        return trasportatoriService.aggiungirimorchio(rimorchio);
    }

    @GetMapping("rimuovirimorchio/{targa}")
    public List<Rimorchio> rimuovirimorchio(@PathVariable String targa){
        // todo
        // staccare rimorchio dal trasportatore

        boolean result = trasportatoriService.rimuovirimorchio(targa);
        return getallrimorchi();
    }


    // ********** FINE RIMORCHI **********


    // ********** AUTISTI *************

    @GetMapping("getallautisti")
    public List<Autista> getallautisti(){
        return trasportatoriService.getallautisti();
    }


    @GetMapping("getautista/{idautista}")
    public Autista getautista(@PathVariable long idautista){
        return trasportatoriService.getautista(idautista);
    }

    @GetMapping("getautistadanome/{nomeautista}")
    public Autista getautistadanome(@PathVariable String nomeautista){
        return trasportatoriService.getautista(nomeautista);
    }

    @PostMapping("aggiungiautista")
    public List<Autista> aggiungiautista(@RequestBody Autista autista){
        boolean result = trasportatoriService.aggiungiautista(autista);
        return getallautisti();
    }

    @GetMapping("rimuoviautista/{idautista}")
    public List<Autista> rimuoviautista(@PathVariable long idautista){
        // todo
        // staccare autista dal trasportatore

        boolean result = trasportatoriService.rimuoviautista(idautista);
        return getallautisti();
    }

    // ******** FINE AUTISTI **********


    @GetMapping("getrimorchiditrasportatore/{idtrasportatore}")
    public List<Rimorchio> getrimorchiditrasportatore(@PathVariable String idtrasportatore){
        return trasportatoriService.getrimorchiditrasportatore(idtrasportatore);
    }

}
