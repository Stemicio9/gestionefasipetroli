package com.petroli.gestionefasipetroli.repositories;

import com.petroli.gestionefasipetroli.entities.QuotazioneGiornaliera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotazioneRepository extends JpaRepository<QuotazioneGiornaliera, Long> {
}
