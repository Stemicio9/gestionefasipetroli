package com.petroli.gestionefasipetroli.repositories;

import com.petroli.gestionefasipetroli.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtenteRepository extends JpaRepository<Utente,String> {


    Optional<Utente> findByUsername(String username);

}
