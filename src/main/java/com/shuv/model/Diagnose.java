package com.shuv.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "diagnoses")
public class Diagnose {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "simptome")
    private String simptome;

    @ManyToMany(targetEntity = Medicines.class,fetch = FetchType.EAGER)
    //@JoinTable(name = "med_diagnose",
    //joinColumns = @JoinColumn(name = "diagnose_id"),
    //inverseJoinColumns = @JoinColumn(name = "medicine_id"))
    private Set<Medicines> medicinesSet;


    public Diagnose(){

    }

    public Diagnose(int id, String name, String simptome, String medicine) {
        this.id = id;
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
