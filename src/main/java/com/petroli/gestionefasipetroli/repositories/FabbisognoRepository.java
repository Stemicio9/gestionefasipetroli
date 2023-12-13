package com.petroli.gestionefasipetroli.repositories;

import com.petroli.gestionefasipetroli.entities.Fabbisogno;
import com.petroli.gestionefasipetroli.entities.PuntoVendita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FabbisognoRepository extends JpaRepository<Fabbisogno, Long> {

    List<Fabbisogno> findAllBySmaltitoFalse();

    List<Fabbisogno> findAllByDataBetween(Date data1, Date data2);

    List<Fabbisogno> findAllByPuntoVendita(PuntoVendita puntoVendita);

    Fabbisogno findById(long id);

}
