package com.thws.eventmanager.infrastructure.components.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PersistenceManager {
    private static final Logger logger = LoggerFactory.getLogger(PersistenceManager.class);

    private static final String PERSISTENCE_UNIT_NAME = "eventmanager";
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    private final EntityManager entityManager;

    private PersistenceManager() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    public static PersistenceManager create() {
        try {
            return new PersistenceManager();
        } catch (Exception e) {
            logger.error("Failed to create PersistenceManager: {}", e.getMessage(), e);
            throw new RuntimeException("Database connection error", e); // Optional: rethrow as RuntimeException
        }
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void close() {
        try {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
                logger.info("EntityManager closed successfully.");
            }
        } catch (Exception e) {
            logger.warn("Failed to close EntityManager: {}", e.getMessage(), e);
        }
    }

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
                entityManagerFactory.close();
                logger.info("EntityManagerFactory closed on application shutdown.");
            }
        }));
    }
}
