package com.petroli.gestionefasipetroli.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class QuotazioneGiornaliera {

    @Id
    @GeneratedValue
    private long id;

    private Date data;

    private double prezzobenzina;

    private double prezzogasolio;

    private double prezzosupreme;

    private double prezzogpl;


    public long getId() {
        return id;
    }


    public Date getData() {
        return data;
    }

    public double getPrezzosupreme() {
        return prezzosupreme;
    }

    public double getPrezzogasolio() {
        return prezzogasolio;
    }

    public double getPrezzobenzina() {
        return prezzobenzina;
    }

    public double getPrezzogpl() {
        return prezzogpl;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setPrezzosupreme(double prezzosupreme) {
        this.prezzosupreme = prezzosupreme;
    }

    public void setPrezzogpl(double prezzogpl) {
        this.prezzogpl = prezzogpl;
    }

    public void setPrezzogasolio(double prezzogasolio) {
        this.prezzogasolio = prezzogasolio;
    }

    public void setPrezzobenzina(double prezzobenzina) {
        this.prezzobenzina = prezzobenzina;
    }


    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!(obj instanceof QuotazioneGiornaliera)) return false;
        QuotazioneGiornaliera other = (QuotazioneGiornaliera) obj;
        return this.id == other.getId();
    }
}
