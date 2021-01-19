package com.petroli.gestionefasipetroli.repositories;

import com.petroli.gestionefasipetroli.entities.Cliente;
import com.petroli.gestionefasipetroli.entities.PuntoVendita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {

    Cliente findByIdcliente(long idcliente);


    Cliente deleteByIdcliente(long idcliente);

    Cliente findByListapuntivenditaContains(PuntoVendita puntoVendita);

    Cliente findByNomecliente(String nome);

}
