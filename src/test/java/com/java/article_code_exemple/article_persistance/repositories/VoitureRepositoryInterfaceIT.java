package com.java.article_code_exemple.article_persistance.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:/database/create_car.sql")
//@SpringBootTest
class VoitureRepositoryInterfaceIT {

    @Autowired
    private CarRepositoryHibernateInterface carRepositoryHibernate;

    @Test
    void shouldRetrieveCar(){
        var voiture = carRepositoryHibernate.getVoitureById(1L);
        assertThat(voiture).isNotNull();
    }
}
