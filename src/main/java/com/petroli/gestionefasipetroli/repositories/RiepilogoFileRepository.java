package com.petroli.gestionefasipetroli.repositories;

import com.petroli.gestionefasipetroli.entities.RiepilogoFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiepilogoFileRepository extends JpaRepository<RiepilogoFile, Long> {
}