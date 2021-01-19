package com.petroli.gestionefasipetroli.repositories;


import com.petroli.gestionefasipetroli.entities.Fabbisogno;
import com.petroli.gestionefasipetroli.entities.Preventivo;
import com.petroli.gestionefasipetroli.entities.Riepilogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RiepilogoRepository extends JpaRepository<Riepilogo,Long> {

    Riepilogo findByFabbisogno(Fabbisogno fabbisogno);

    List<Riepilogo> findAllByFabbisogno_DataBetween(Date data1, Date data2);

    Riepilogo findByPreventivo(Preventivo preventivo);


}
