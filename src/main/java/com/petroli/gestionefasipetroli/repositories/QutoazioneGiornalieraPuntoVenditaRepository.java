package com.petroli.gestionefasipetroli.repositories;

import com.petroli.gestionefasipetroli.entities.QuotazioneGiornalieraPuntoVendita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QutoazioneGiornalieraPuntoVenditaRepository extends JpaRepository<QuotazioneGiornalieraPuntoVendita,Long> {
}
