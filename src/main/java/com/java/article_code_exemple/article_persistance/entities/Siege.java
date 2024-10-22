package com.java.article_code_exemple.article_persistance.entities;

import jakarta.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Siege {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Voiture voiture;

    @Column
    private String color;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "VIS")
    private Set<Vis> vis = new HashSet<>();

    public Set<Vis> getVis() {
        return Collections.unmodifiableSet(this.vis);
    }

    @Embeddable
    public static class Vis {

        @Column
        private String forme;

        @Column
        private int longueur;

        @Column
        private int diametre;

    }
}
