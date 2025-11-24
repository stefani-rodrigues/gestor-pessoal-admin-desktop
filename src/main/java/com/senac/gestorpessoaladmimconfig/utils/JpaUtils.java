package com.senac.gestorpessoaladmimconfig.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtils {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("exemploPU");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
