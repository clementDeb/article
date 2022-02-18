package com.java.persistance.entities;

import javax.persistence.*;

@Entity
public class Siege {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Voiture voiture;

    @Column
    private String color;

}
