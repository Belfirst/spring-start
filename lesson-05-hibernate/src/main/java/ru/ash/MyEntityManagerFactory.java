package ru.ash;

import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;

@Component
public class MyEntityManagerFactory {

    private final EntityManagerFactory entityManagerFactory = new Configuration().
            configure("hibernate.cfg.xml").buildSessionFactory();

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}
