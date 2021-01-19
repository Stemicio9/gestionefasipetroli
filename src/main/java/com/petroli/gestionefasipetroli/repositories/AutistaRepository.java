package com.petroli.gestionefasipetroli.repositories;

import com.petroli.gestionefasipetroli.entities.Autista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutistaRepository extends JpaRepository<Autista, Long> {

    Autista findByIdautista(long idautista);

    Autista findByNomeautista(String nomeautista);

}
