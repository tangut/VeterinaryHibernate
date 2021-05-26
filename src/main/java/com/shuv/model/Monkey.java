package com.shuv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "monkey")
public class Monkey extends Pet{

    @Column(name = "shaman")
    private boolean isShaman;

    public Monkey(){

    }

    public Monkey(String name, String kind, String breed, boolean isShaman, User master) {
        super(name, kind, breed);
        this.isShaman = isShaman;
        setMaster(master);
    }

    public boolean isShaman() {
        return isShaman;
    }

    public void setShaman(boolean shaman) {
        isShaman = shaman;
    }
}
