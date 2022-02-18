package com.java.persistance.repositories;

import com.java.persistance.entities.Voiture;
import com.java.persistance.entities.Roue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowableOfType;

//@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:/database/create_car.sql")
@SpringBootTest
class VoitureRepositoryIT {

//    @Autowired
//    private CarRepository carRepository;

    @Autowired
    private CarRepositoryHibernate carRepositoryHibernate;

    @Test
    void shouldRaiseFetchException(){
        Exception exception = catchThrowableOfType(() -> carRepositoryHibernate.retrieveCompleteCarError(1), Exception.class);
        assertThat(exception.getMessage()).contains("org.hibernate.loader.MultipleBagFetchException: cannot simultaneously fetch multiple bags");
    }

    @Test
    @Transactional
    void shouldRetrieveCar(){
        Voiture voiture = carRepositoryHibernate.retrieveCompleteCarAvecListeOrdonnee(1);
        assertThat(voiture).isNotNull();
        assertThat(voiture.getRoues().iterator().next().getMarque()).isEqualTo("brand4");
    }

    @Test
    @Transactional
    void shouldRetrieveCarWithCartesianProduct(){
        List<Voiture> voitures = carRepositoryHibernate.retrieveAllCarCartesian();
        assertThat(voitures.size()).isEqualTo(12);
    }

//    @Test
//    @Transactional
//    void shouldRetrieveCarWithoutCartesianProduct(){
//        List<Voiture> voitures = carRepositoryHibernate.retrieveAllCarComplete();
//        assertThat(voitures.size()).isEqualTo(2);
//        voitures.forEach(car -> {
//            assertThat(car.getRoues().size()).isEqualTo(2);
//            assertThat(car.getSeats().size()).isEqualTo(2);
//        });
//    }

    @Test
    @Transactional
    void shouldRetrieveWheelWithN1QUery(){
        List<Roue> roues = carRepositoryHibernate.retrieveWheels();
        roues.forEach(roue -> {
            String color = roue.getCar().getCouleur();
            assertThat(color).isNotNull();
        });
    }

    @Test
    @Transactional
    void shouldRetrieveCarLazy(){
        Voiture voiture = carRepositoryHibernate.retrieveCar(1);
        assertThat(voiture).isNotNull();
        assertThat(voiture.getRoues().iterator().next().getMarque()).isEqualTo("brand4");
    }


}
