package com.petroli.gestionefasipetroli.repositories;

import com.petroli.gestionefasipetroli.entities.Concorrente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcorrenteRepository extends JpaRepository<Concorrente,Long> {
}
