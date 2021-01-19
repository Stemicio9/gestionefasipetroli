package com.petroli.gestionefasipetroli.services;

import com.petroli.gestionefasipetroli.entities.Cliente;
import com.petroli.gestionefasipetroli.entities.PuntoVendita;
import com.petroli.gestionefasipetroli.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PuntiVenditaService puntiVenditaService;


    public Cliente aggiungicliente(Cliente cliente){
        try{
            aggiungipuntivenditafdicliente(cliente.getListapuntivendita());
            Cliente result = clienteRepository.saveAndFlush(cliente);

            return result;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public Cliente getclientedaid(long idcliente){
        return clienteRepository.findByIdcliente(idcliente);
    }


    public List<PuntoVendita> getpuntivenditadiattivita(long idcliente){
        Cliente cliente = getclientedaid(idcliente);
        return cliente.getListapuntivendita();
    }


    public List<Cliente> getallclienti(){
        return clienteRepository.findAll();
    }



    public Cliente updatecliente(Cliente cliente){
        return aggiungicliente(cliente);
    }


    public void aggiungipuntivenditafdicliente(List<PuntoVendita> listapuntivendita){
        for(PuntoVendita p : listapuntivendita){
            try {
                puntiVenditaService.aggiungipuntovendita(p);
            }catch(Exception e){
                System.out.println("CLIENTE GIA' ESISTENTE");
                e.printStackTrace();
            }
        }
    }



    public List<PuntoVendita> getallpuntivenditasenzacliente(){
        List<Cliente> tutticlienti = getallclienti();
        List<PuntoVendita> tuttipuntivendita = puntiVenditaService.getallpuntivendita();
        for(Cliente c : tutticlienti){
            tuttipuntivendita.removeAll(c.getListapuntivendita());
        }
        return tuttipuntivendita;
    }

    @Transactional
    public List<Cliente> eliminacliente(long idcliente){
        try {
            Cliente curr = clienteRepository.findByIdcliente(idcliente);
            curr.setListapuntivendita(new LinkedList<>());
            clienteRepository.delete(curr);
            return getallclienti();
        }catch(Exception e){
            e.printStackTrace();
            return getallclienti();
        }
    }


    public Cliente findbynome(String nomecliente){
        return clienteRepository.findByNomecliente(nomecliente);
    }



}
