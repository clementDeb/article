package com.java.persistance.entities;

import javax.persistence.*;

@Entity
public class Roue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String taille;

    @Column
    private String marque;

    @ManyToOne(fetch = FetchType.LAZY)
    private Voiture voiture;

    public String getTaille() {
        return taille;
    }

    public void setTaille(String size) {
        this.taille = size;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String brand) {
        this.marque = brand;
    }

    public Voiture getCar() {
        return voiture;
    }

    public void setCar(Voiture voiture) {
        this.voiture = voiture;
    }
}
