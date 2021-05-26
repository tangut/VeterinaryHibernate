package com.shuv.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "diagnoses")
public class Diagnose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "simptome")
    private String simptome;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable (name="med_diagnose",
            joinColumns=@JoinColumn (name="diagnose_id"),
            inverseJoinColumns=@JoinColumn(name="medicine_id"))
     @JsonManagedReference
    private Set<Medicines> medicinesSet;

    public Set<Medicines> getMedicinesSet() {
        return medicinesSet;
    }

    public void setMedicinesSet(Set<Medicines> medicinesSet) {
        this.medicinesSet = medicinesSet;
    }

    public Diagnose(){

    }

    public Diagnose(String name, String simptome) {
        this.name = name;
        this.simptome = simptome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSimptome() {
        return simptome;
    }

    public void setSimptome(String simptome) {
        this.simptome = simptome;
    }


}
