package com.petroli.gestionefasipetroli.repositories;

import com.petroli.gestionefasipetroli.entities.Bonifico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BonificoRepository extends JpaRepository<Bonifico, Long> {

}
