package com.shuv.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pets")
@Inheritance(strategy = InheritanceType.JOINED)
public class Pet {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "kind")
    private String kind;
    @Column(name = "breed")
    private String breed;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User master;


    public Pet(){

    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name =  "diagnose_id")
    @JsonManagedReference
    private Diagnose diagnose;

    public Pet(Integer id, String name, String kind, String breed, Diagnose diagnose, User master) {
        this.id = id;
        this.name = name;
        this.kind = kind;
        this.breed = breed;
        this.diagnose = diagnose;
        this.master = master;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return id.equals(pet.id) &&
                name.equals(pet.name) &&
                kind.equals(pet.kind) &&
                breed.equals(pet.breed) &&
                diagnose.equals(pet.diagnose);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, kind, breed, diagnose);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

        public Diagnose getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(Diagnose diagnose) {
        this.diagnose = diagnose;
    }
}
