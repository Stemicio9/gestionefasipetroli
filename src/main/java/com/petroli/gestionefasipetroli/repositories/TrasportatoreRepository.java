package com.petroli.gestionefasipetroli.repositories;

import com.petroli.gestionefasipetroli.entities.Atk;
import com.petroli.gestionefasipetroli.entities.Autista;
import com.petroli.gestionefasipetroli.entities.Rimorchio;
import com.petroli.gestionefasipetroli.entities.Trasportatore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrasportatoreRepository extends JpaRepository<Trasportatore, Long> {


    Trasportatore findByNometrasportatore(String nometrasportatore);



    Trasportatore findByListaatkContains(Atk atk);

    Trasportatore findByListaautistiContains(Autista autista);

    Trasportatore findByListarimorchiContains(Rimorchio rimorchio);

}
