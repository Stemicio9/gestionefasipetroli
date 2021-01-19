package com.petroli.gestionefasipetroli.repositories;

import com.petroli.gestionefasipetroli.entities.Atk;
import com.petroli.gestionefasipetroli.entities.BaseDiCarico;
import com.petroli.gestionefasipetroli.entities.Fabbisogno;
import com.petroli.gestionefasipetroli.entities.Trasporto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TrasportoRepository extends JpaRepository<Trasporto,Long> {

    Trasporto findById(long id);

    Trasporto findByFabbisogno(Fabbisogno fabbisogno);

    List<Trasporto> findAllByAtk(Atk atk);

    List<Trasporto> findAllByDatadicaricazione(Date datadicaricazione);

    List<Trasporto> findAllByFabbisogno_Basedicarico(BaseDiCarico baseDiCarico);

    List<Trasporto> findAllByAtkAndDatadicaricazione(Atk atk, Date datadicaricazione);

    List<Trasporto> findAllByAtkAndFabbisogno_Basedicarico(Atk atk, BaseDiCarico baseDiCarico);

    List<Trasporto> findAllByAtkAndDatadicaricazioneAndFabbisogno_Basedicarico(Atk atk, Date datadicaricazione, BaseDiCarico basedicarico);

}
