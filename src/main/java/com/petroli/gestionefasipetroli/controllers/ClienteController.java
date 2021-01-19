package com.petroli.gestionefasipetroli.controllers;


import com.petroli.gestionefasipetroli.entities.Cliente;
import com.petroli.gestionefasipetroli.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;


    @GetMapping("getall")
    private List<Cliente> getallclienti(){
        return clienteService.getallclienti();
    }

    @PostMapping("aggiungi")
    public boolean aggiungicliente(@RequestBody Cliente cliente){
        Cliente result =  clienteService.aggiungicliente(cliente);
        if(result == null) return false;
        return true;
    }

    @GetMapping("getcliente/{idcliente}")
    public Cliente getcliente(@PathVariable long idcliente){
        return clienteService.getclientedaid(idcliente);
    }


    @PostMapping("aggiornacliente")
    public Cliente aggiornacliente(@RequestBody Cliente cliente){
        return clienteService.updatecliente(cliente);
    }

    @GetMapping("eliminacliente/{idcliente}")
    public List<Cliente> eliminacliente(@PathVariable long idcliente){

        return clienteService.eliminacliente(idcliente);
    }





}
