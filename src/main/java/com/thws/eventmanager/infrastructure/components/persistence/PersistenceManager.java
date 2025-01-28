package com.thws.eventmanager.infrastructure.components.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PersistenceManager implements AutoCloseable {
    private static final String PERSISTENCE_UNIT_NAME = "eventmanager";
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    private final EntityManager entityManager;

    private PersistenceManager() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    public static PersistenceManager create() {
        return new PersistenceManager();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public void close() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
        if (entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}
