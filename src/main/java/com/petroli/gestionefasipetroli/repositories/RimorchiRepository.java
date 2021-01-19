package com.petroli.gestionefasipetroli.repositories;

import com.petroli.gestionefasipetroli.entities.Rimorchio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RimorchiRepository extends JpaRepository<Rimorchio, Long> {

    Rimorchio findByTarga(String targa);

}
