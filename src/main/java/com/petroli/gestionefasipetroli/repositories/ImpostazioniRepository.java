package com.petroli.gestionefasipetroli.repositories;

import com.petroli.gestionefasipetroli.entities.Impostazioni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImpostazioniRepository extends JpaRepository<Impostazioni,String> {

    Impostazioni findByNomeimpostazione(String nomeimpostazione);

}
