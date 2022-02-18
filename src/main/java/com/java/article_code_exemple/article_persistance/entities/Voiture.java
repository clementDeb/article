package com.java.article_code_exemple.article_persistance.entities;

import javax.persistence.*;
import java.util.*;

@Entity
public class Voiture  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String modele;

    @Column
    private String couleur;


    @OneToMany(mappedBy = "voiture", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    //@OrderColumn(name = "wheel_order")
    private final Set<Roue> roues = new HashSet<>();

    @OneToMany(mappedBy = "voiture", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private final Set<Siege> sieges = new HashSet<>();

    public void addWheel(Roue roue){
        this.roues.add(roue);
        roue.setCar(this);
    }

    public void removeWheel(Roue roue){
        this.roues.remove(roue);
        roue.setCar(null);
    }

    public Set<Roue> getRoues(){
        return Collections.unmodifiableSet(this.roues);
    }

//    public Set<Siege> getSeats(){
//        return Collections.unmodifiableSet(this.sieges);
//    }

    public String getCouleur(){
        return this.couleur;
    }

}
