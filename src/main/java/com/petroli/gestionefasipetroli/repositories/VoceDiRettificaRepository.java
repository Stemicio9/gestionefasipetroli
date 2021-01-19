package com.petroli.gestionefasipetroli.repositories;

import com.petroli.gestionefasipetroli.entities.VoceDiRettifica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VoceDiRettificaRepository extends JpaRepository<VoceDiRettifica,Long> {

    VoceDiRettifica findByNomevoce(String nomevoce);

    VoceDiRettifica findByIdvocedirettifica(long id);

}
