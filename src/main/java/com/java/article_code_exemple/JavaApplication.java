package com.java.persistance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories
public class JavaPersisanceApplication {
    public static void main(String... args){
        SpringApplication.run(JavaPersisanceApplication.class, args);
    }
}
