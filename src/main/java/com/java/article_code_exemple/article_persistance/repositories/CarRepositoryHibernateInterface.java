package com.java.article_code_exemple.article_persistance.repositories;

import com.java.article_code_exemple.article_persistance.entities.Roue;
import com.java.article_code_exemple.article_persistance.entities.Voiture;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepositoryHibernateInterface extends JpaRepository<Voiture, Long> {

    public Voiture getVoitureById(long id);
}
