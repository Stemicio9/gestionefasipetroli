package com.petroli.gestionefasipetroli.repositories;

import com.petroli.gestionefasipetroli.entities.Atk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtkRepository extends JpaRepository<Atk, Long> {

    Atk findByIdatk(long idatk);

    Atk findByCodice(String codice);

    Atk findByTarga(String targa);

}
