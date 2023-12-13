package com.petroli.gestionefasipetroli.repositories;


import com.petroli.gestionefasipetroli.entities.PuntoVendita;
import com.petroli.gestionefasipetroli.entities.QuotazioneGiornalieraPuntoVendita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PuntiVenditaRepository extends JpaRepository<PuntoVendita,Long> {

    PuntoVendita findByIdpunto(long id);

    PuntoVendita findByQuotazioniContains(QuotazioneGiornalieraPuntoVendita quotazioneGiornalieraPuntoVendita);

}
