package com.petroli.gestionefasipetroli.repositories;

import com.petroli.gestionefasipetroli.entities.BaseDiCarico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseDiCaricoRepository extends JpaRepository<BaseDiCarico, Long> {

    BaseDiCarico findByIdbasedicarico(long idbasedicarico);

    BaseDiCarico findByNomebasedicarico(String nomebasedicarico);

}
