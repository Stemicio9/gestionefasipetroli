package com.petroli.gestionefasipetroli.controllers;


import com.petroli.gestionefasipetroli.entities.Utente;
import com.petroli.gestionefasipetroli.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/utente")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;


    @GetMapping("creautentedefault")
    public List<Utente> creautentedefault(){
        return creautente("admin" , "Partenopea2021" , 0);
    }

    @GetMapping("creautente/{username}/{password}/{ruolo}")
    public List<Utente> creautente(@PathVariable String username,@PathVariable String password, @PathVariable int ruolo){
        return utenteService.creaUtente(username,password,ruolo);

    }

    @PostMapping("creautente")
    public List<Utente> creautente(@RequestBody Utente utente){
        return creautente(utente.getPassword(),utente.getUsername(),utente.getRuolo());
    }

    @GetMapping("getall")
    public List<Utente> getallutenti(){
        return utenteService.getallutenti();
    }


}
