package com.java.article_code_exemple.article_builder;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Set;

public class Livre {

    //donnees obligatoires
    private String titre;
    private String auteur;
    //Donnees facultatives
    private BigDecimal prix;
    private int nombrePage;

    private Livre(LivreBuilder builder) {
        titre = builder.titre;
        auteur = builder.auteur;
        prix = builder.prix;
        nombrePage = builder.nombrePage;
    }

    private Livre(LivreBuilderFluentInterface builder) {
        titre = builder.titre;
        auteur = builder.auteur;
        prix = builder.prix;
        nombrePage = builder.nombrePage;
    }

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public int getNombrePage() {
        return nombrePage;
    }

    public static LivreTitre builder(){
        return LivreBuilderFluentInterface.get();
    }

    public static class LivreBuilder {

        private String titre;
        private String auteur;
        private BigDecimal prix;
        private int nombrePage;

        public LivreBuilder titre(String titre) {
            this.titre = titre;
            return this;
        }

        public LivreBuilder auteur(String auteur) {
            this.auteur = auteur;
            return this;
        }

        public LivreBuilder prix(BigDecimal prix) {
            this.prix = prix;
            return this;
        }

        public LivreBuilder nombrePage(int nombrePage) {
            this.nombrePage = nombrePage;
            return this;
        }

        public Livre build() {
            donnesObligatoiresPresentes(this);
            return new Livre(this);
        }

        private void donnesObligatoiresPresentes(LivreBuilder builder) {
            if (null == builder.auteur || null == builder.titre) {
                throw new IllegalArgumentException("les données auteur et titre doivent êtres renseignées");
            }
        }

    }

    public static class LivreBuilderFluentInterface implements LivreTitre, LivreAuteur, LivreReste{

        @NotEmpty
        private String titre;
        @NotEmpty
        private String auteur;
        private BigDecimal prix;
        private int nombrePage;

        private LivreBuilderFluentInterface(){}


        private static LivreTitre get(){
            return new LivreBuilderFluentInterface();
        }

        @Override
        public LivreAuteur titre(String titre) {
            this.titre = titre;
            return this;
        }

        @Override
        public LivreReste auteur(String auteur) {
            this.auteur = auteur;
            return this;
        }

        @Override
        public LivreBuilderFluentInterface prix(BigDecimal prix) {
            this.prix = prix;
            return this;
        }

        @Override
        public LivreBuilderFluentInterface nombrePage(int nombrePage) {
            this.nombrePage = nombrePage;
            return this;
        }

        @Override
        public Livre build() {
            validationDonneeObligatoires();
            return new Livre(this);
        }

        private void validationDonneeObligatoires (){
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set<ConstraintViolation<LivreBuilderFluentInterface>> violations = validator.validate(this);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }
        }
    }

    interface LivreTitre {
        LivreAuteur titre (String titre);
    }

    interface LivreAuteur {
        LivreReste auteur (String auteur);
    }

    interface LivreReste{
        LivreBuilderFluentInterface prix(BigDecimal prix);
        LivreBuilderFluentInterface nombrePage (int nombrePage);
        Livre build();
    }

}
