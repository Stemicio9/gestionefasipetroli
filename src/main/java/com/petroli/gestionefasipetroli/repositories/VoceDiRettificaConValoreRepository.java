package com.petroli.gestionefasipetroli.repositories;

import com.petroli.gestionefasipetroli.entities.VoceDiRettificaConValore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoceDiRettificaConValoreRepository extends JpaRepository<VoceDiRettificaConValore,Long> {
}
