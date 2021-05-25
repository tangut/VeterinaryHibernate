package com.shuv.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "medicines")
public class Medicines {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "description")
    private String description;

    @ManyToMany(targetEntity = Diagnose.class)
    private List<Diagnose> diagnoseList;

    public Medicines(){

    }

    public Medicines(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicines medicines = (Medicines) o;
        return id.equals(medicines.id) &&
                description.equals(medicines.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }
}
