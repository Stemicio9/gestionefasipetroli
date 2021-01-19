package com.petroli.gestionefasipetroli.repositories;

import com.petroli.gestionefasipetroli.entities.Fornitore;
import com.petroli.gestionefasipetroli.entities.QuotazioneGiornaliera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornitoreRepository extends JpaRepository<Fornitore, Long> {

    Fornitore findByNomefornitore(String nomefornitore);

    Fornitore findByIdfornitore(long idfornitore);

    Fornitore findByQuotazioniContains(QuotazioneGiornaliera quotazioneGiornaliera);

}
