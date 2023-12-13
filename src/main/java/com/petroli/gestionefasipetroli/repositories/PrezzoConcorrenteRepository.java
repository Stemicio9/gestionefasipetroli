package com.petroli.gestionefasipetroli.repositories;

import com.petroli.gestionefasipetroli.entities.PrezzoConcorrente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PrezzoConcorrenteRepository extends JpaRepository<PrezzoConcorrente, Long> {

    List<PrezzoConcorrente> findAllByData(Date data);

    List<PrezzoConcorrente> findAllByConcorrente_IdAndDataBeforeOrderByDataDesc(long id,Date data);



    PrezzoConcorrente findFirstByIdOrderByDataDesc(long id);
}
