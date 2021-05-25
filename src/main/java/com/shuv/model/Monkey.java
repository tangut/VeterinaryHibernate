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

    public Monkey(Integer id, String name, String kind, String breed, User master, Diagnose diagnose, boolean isShaman) {
        super(id, name, kind, breed, diagnose, master);
        this.isShaman = isShaman;
    }

    public boolean isShaman() {
        return isShaman;
    }

    public void setShaman(boolean shaman) {
        isShaman = shaman;
    }
}
