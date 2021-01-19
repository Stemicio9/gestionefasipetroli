package com.petroli.gestionefasipetroli.controllers;


import com.petroli.gestionefasipetroli.entities.Utente;
import com.petroli.gestionefasipetroli.services.AuthenticationService;
import com.petroli.gestionefasipetroli.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/amministrazione")
public class AmministrazioneController {


    @Autowired
    private UtenteService utenteService;

    @Autowired
    private AuthenticationService authenticationService;


    @GetMapping("getallutenti")
    public List<Utente> getallutenti(){
        return utenteService.getallutenti();
    }

    @GetMapping("checklogin")
    public Utente checklogin(){
        return authenticationService.getcurrentauthentication();
    }

    @PostMapping("rimuovi")
    public List<Utente> rimuoviutente(@RequestBody Utente utente){
        return utenteService.rimuoviutente(utente);
    }

    @GetMapping("creautente/{username}/{password}/{ruolo}")
    public List<Utente> creautente(@PathVariable String username,@PathVariable String password, @PathVariable int ruolo){
        return utenteService.creaUtente(username,password,ruolo);
    }


    @PostMapping("creautente")
    public List<Utente> creautente(@RequestBody Utente utente){
        return creautente(utente.getUsername(),utente.getPassword(),utente.getRuolo());
    }

}
