package com.petroli.gestionefasipetroli.entities;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Utente {


    @Id
    private String username;

    private String password;

    private String passwordinchiaro;


    // 0 = ADMIN , 1 = VIEWER, 2 = CLIENTE
    private int ruolo;

    @OneToOne
    private Cliente cliente;


    public Utente(){

    }

    public Utente(String username,String password, String passwordinchiaro,int ruolo){
        this.password=password;
        this.username = username;
        this.passwordinchiaro=passwordinchiaro;
        this.ruolo = ruolo;
    }

    public Utente(Utente u){
        this.username = u.getUsername();
        this.password = u.getPassword();
        this.passwordinchiaro= u.getPasswordinchiaro();
        this.ruolo = u.getRuolo();
    }


    public int getRuolo() {
        return ruolo;
    }

    public String getPassword() {
        return password;
    }


    public String getUsername() {
        return username;
    }

    public String getPasswordinchiaro() {
        return passwordinchiaro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setRuolo(int ruolo) {
        this.ruolo = ruolo;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPasswordinchiaro(String passwordinchiaro) {
        this.passwordinchiaro = passwordinchiaro;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
