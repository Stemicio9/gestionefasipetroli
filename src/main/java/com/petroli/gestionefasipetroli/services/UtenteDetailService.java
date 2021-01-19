package com.petroli.gestionefasipetroli.services;


import com.petroli.gestionefasipetroli.entities.Utente;
import com.petroli.gestionefasipetroli.entities.UtenteDettaglio;
import com.petroli.gestionefasipetroli.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtenteDetailService implements UserDetailsService {

    @Autowired
    private UtenteRepository utenteRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Utente> usersOptional = utenteRepository.findByUsername(username);

        usersOptional
                .orElseThrow(() -> new UsernameNotFoundException("Username non trovato!"));
        return usersOptional
                .map(UtenteDettaglio::new)
                .get();
    }



}
