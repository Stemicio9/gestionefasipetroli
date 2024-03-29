package com.petroli.gestionefasipetroli.services;

import com.petroli.gestionefasipetroli.entities.Utente;
import com.petroli.gestionefasipetroli.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;


    public List<Utente> creaUtente(String username, String password,int ruolo){
        String passwordencoded = encoder.encode(password);
        Utente u = new Utente(username,passwordencoded,password,ruolo);
        try{
            utenteRepository.save(u);
            return getallutenti();
        }catch(Exception e){
            e.printStackTrace();
            return getallutenti();
        }
    }

    public List<Utente> creautente(Utente utente){
        String passwordencoded = encoder.encode(utente.getPassword());
        utente.setPasswordinchiaro(utente.getPassword());
        utente.setPassword(passwordencoded);
        try{
            utenteRepository.save(utente);
            return getallutenti();
        }catch(Exception e){
            e.printStackTrace();
            return getallutenti();
        }
    }


    public List<Utente> getallutenti(){
        return utenteRepository.findAll();
    }


    public Utente getUtenteDaUsername(String username){
        Optional<Utente> usersOptional = utenteRepository.findByUsername(username);
        usersOptional
                .orElseThrow(() -> new UsernameNotFoundException("Username non trovato!"));
        return usersOptional.get();
    }

    public List<Utente> rimuoviutente(Utente utente){
        if(utente.getUsername().equals("admin")) return getallutenti();
        try{
            utenteRepository.delete(utente);
            return getallutenti();
        }catch(Exception e){
            e.printStackTrace();
            return getallutenti();
        }
    }

}
