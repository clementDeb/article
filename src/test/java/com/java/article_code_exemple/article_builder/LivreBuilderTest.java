package com.java.article_code_exemple.article_builder;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;

class LivreBuilderTest {

    @Test
    void doitConstruireLivreAvecBuilderClassique(){
        //given

        //when
        Livre livre = new Livre.LivreBuilder()
                .titre("titre")
                .auteur("auteur")
                .nombrePage(35)
                .prix(BigDecimal.TEN)
                .build();
        //then
        assertThat(livre.getAuteur()).isEqualTo("auteur");
        assertThat(livre.getTitre()).isEqualTo("titre");
        assertThat(livre.getNombrePage()).isEqualTo(35);
        assertThat(livre.getPrix()).isEqualByComparingTo(BigDecimal.TEN);
    }

    //mettre en commentaire la methode de verificartion pour faire passer ce TU
    @Test
    void doitConstruireLivreVideAvecBuilderClassique(){
        //given

        //when
        Livre livre = new Livre.LivreBuilder().build();
        //then
        assertThat(livre.getAuteur()).isNull();
        assertThat(livre.getTitre()).isNull();
        assertThat(livre.getNombrePage()).isZero();
        assertThat(livre.getPrix()).isNull();
    }

    @Test
    void doitleverExceptionSiDOnneesObligatoiresNonRenseignees(){
        //given
        Livre.LivreBuilder builder = new Livre.LivreBuilder();
        //when
        Exception exception = catchThrowableOfType(builder::build, IllegalArgumentException.class);
        //then
        assertThat(exception).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("les données auteur et titre doivent êtres renseignées");
    }

    @Test
    void doitleverRenvoyerUnLivreAvecDonneesObligatoires(){
        //given

        //when

            Livre livre = Livre.builder()
                .titre("titre")
                .auteur("auteur")
                .build();

        //then
        assertThat(livre.getAuteur()).isEqualTo("auteur");
        assertThat(livre.getTitre()).isEqualTo("titre");
        assertThat(livre.getNombrePage()).isZero();
        assertThat(livre.getPrix()).isNull();
    }

    @Test
    void doitleverRenvoyerUnLivreAvecDonneesCompletes(){
        //given

        //when
        Livre livre = Livre.builder()
                .titre("titre")
                .auteur("auteur")
                .prix(BigDecimal.ONE)
                .nombrePage(35)
                .build();

        //then
        assertThat(livre.getAuteur()).isEqualTo("auteur");
        assertThat(livre.getTitre()).isEqualTo("titre");
        assertThat(livre.getNombrePage()).isEqualTo(35);
        assertThat(livre.getPrix()).isEqualByComparingTo(BigDecimal.ONE);
    }

}