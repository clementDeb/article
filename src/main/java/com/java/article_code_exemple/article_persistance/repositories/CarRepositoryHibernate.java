package com.java.article_code_exemple.article_persistance.repositories;

import com.java.article_code_exemple.article_persistance.entities.Roue;
import com.java.article_code_exemple.article_persistance.entities.Voiture;
import org.hibernate.jpa.QueryHints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CarRepositoryHibernate {

    @Autowired
    EntityManager em;

    public Voiture retrieveCompleteCarError(int id){
        TypedQuery<Voiture> query = em.createQuery("select voiture from Voiture voiture left join fetch voiture.roues as roues left join fetch voiture.sieges as seats where voiture.id=:id", Voiture.class);
        return query.setParameter("id", id).getSingleResult();
    }

    public List<Voiture> retrieveAllCar(){
        TypedQuery<Voiture> query = em.createQuery("select distinct voiture from Voiture voiture left join fetch voiture.roues as roues", Voiture.class).setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, true);
        return query.getResultList();
    }

    public List<Voiture> retrieveAllCarCartesian(){
        TypedQuery<Voiture> query = em.createQuery("select voiture from Voiture voiture left join fetch voiture.roues as roues left join fetch voiture.sieges as seats", Voiture.class);
        return query.getResultList();
    }

    public List<Voiture> retrieveAllCarComplete(){
        List<Voiture> voitures = em.createQuery("""
            select distinct voiture from Voiture voiture 
            left join fetch voiture.roues as roues
            """, Voiture.class)
                .getResultList();

        voitures = em.createQuery("""
            select distinct voiture from Voiture voiture 
            left join fetch voiture.sieges as siege 
            where voiture in (:voitures)
            """, Voiture.class)
                .setParameter("voitures", voitures)
                .getResultList();

        return voitures;
    }

    //@Transactional
    public Voiture retrieveCompleteCarAvecListeOrdonnee(int id){
        TypedQuery<Voiture> query = em.createQuery("""
            select voiture from Voiture voiture 
            where voiture.id=:id
            """, Voiture.class);
        return query.setParameter("id", id).getSingleResult();
    }

    public List<Roue> retrieveWheels(){
        return em.createQuery("select wheel from Roue wheel", Roue.class).getResultList();
    }

    public Voiture retrieveCar(int id){
        return em.createQuery("""
            select voiture from Voiture voiture 
            where voiture.id=:id
            """, Voiture.class)
                .setParameter("id", id).getSingleResult();
    }
}
