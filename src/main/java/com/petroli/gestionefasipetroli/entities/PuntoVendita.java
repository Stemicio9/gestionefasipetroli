package com.petroli.gestionefasipetroli.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Map;


@Entity
public class PuntoVendita {

    @Id
    @GeneratedValue
    private long idpunto;

    private String codicedestinazione;

    private String nome;

    private String via;

    private String citta;

    private String provincia;

    private String cap;

    @OneToMany(cascade = CascadeType.ALL)
    private List<VoceDiRettificaConValore> listavocidirettifica;

    @OneToMany(cascade = CascadeType.ALL)
    private List<QuotazioneGiornalieraPuntoVendita> quotazioni;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Concorrente> listaconcorrenti;

    private String email;

    private boolean allservito;



    public String getCap() {
        return cap;
    }

    public String getCitta() {
        return citta;
    }

    public long getIdpunto() {
        return idpunto;
    }

    public String getNome() {
        return nome;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getVia() {
        return via;
    }

    public String getCodicedestinazione() {
        return codicedestinazione;
    }

    public List<VoceDiRettificaConValore> getListavocidirettifica() {
        return listavocidirettifica;
    }

    public List<QuotazioneGiornalieraPuntoVendita> getQuotazioni() {
        return quotazioni;
    }

    public String getEmail() {
        return email;
    }

    public List<Concorrente> getListaconcorrenti() {
        return listaconcorrenti;
    }

    public boolean isAllservito() {
        return allservito;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }


    public void setIdpunto(long idpunto) {
        this.idpunto = idpunto;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public void setCodicedestinazione(String codicedestinazione) {
        this.codicedestinazione = codicedestinazione;
    }

    public void setListavocidirettifica(List<VoceDiRettificaConValore> listavocidirettifica) {
        this.listavocidirettifica = listavocidirettifica;
    }

    public void setQuotazioni(List<QuotazioneGiornalieraPuntoVendita> quotazioni) {
        this.quotazioni = quotazioni;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAllservito(boolean allservito) {
        this.allservito = allservito;
    }

    public void setListaconcorrenti(List<Concorrente> listaconcorrenti) {
        this.listaconcorrenti = listaconcorrenti;
    }

    public Concorrente findconcorrentebyname(String nome){
        for(Concorrente curr : listaconcorrenti){
            try {
                if (curr.getNomeconcorrente().equals(nome)) {
                    return curr;
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

}
