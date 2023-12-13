package com.petroli.gestionefasipetroli.entities;

import org.hibernate.annotations.GenericGenerator;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class RiepilogoFile {


    @Id
    @GeneratedValue
    private long id;



    private String name;
    private String type;
    @Lob
    private byte[] data;



    public RiepilogoFile() {
    }


    public RiepilogoFile(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public byte[] getData() {
        return data;
    }
    public void setData(byte[] data) {
        this.data = data;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!(obj instanceof RiepilogoFile)) return false;
        RiepilogoFile that = (RiepilogoFile) obj;
        return this.id == that.getId();
    }
}
