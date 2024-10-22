package com.java.article_code_exemple.article_persistance.entities;

import jakarta.persistence.*;

@Entity
public class Vis {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String forme;

    @Column
    private int longueur;

    @Column
    private int diametre;

    @ManyToOne(fetch = FetchType.LAZY)
    private Siege siege;

}
