package com.petroli.gestionefasipetroli.repositories;

import com.petroli.gestionefasipetroli.entities.Fabbisogno;
import com.petroli.gestionefasipetroli.entities.Preventivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreventivoRepository extends JpaRepository<Preventivo, Long> {

    Preventivo findByRiferimento(Fabbisogno riferimento);

    Preventivo findById(long id);

}
