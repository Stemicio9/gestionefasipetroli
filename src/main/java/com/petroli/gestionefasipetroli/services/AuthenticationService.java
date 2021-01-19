package com.petroli.gestionefasipetroli.services;


import com.petroli.gestionefasipetroli.entities.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UtenteService utenteService;


    public Utente getcurrentauthentication(){
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = dipendedalprincipal(auth);
            return utenteService.getUtenteDaUsername(username);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }




    private String dipendedalprincipal(Authentication auth){
        if(auth.getPrincipal() instanceof UserDetails){
            return (((UserDetails) auth.getPrincipal()).getUsername());
        }
        return auth.getPrincipal().toString();
    }

}
