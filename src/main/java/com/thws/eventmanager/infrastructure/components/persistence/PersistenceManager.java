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

    private final ThreadLocal<EntityManager> threadLocalEntityManager = ThreadLocal.withInitial(entityManagerFactory::createEntityManager);

    private PersistenceManager() {}

    public static PersistenceManager create() {
        return new PersistenceManager();
    }

    public EntityManager getEntityManager() {
        EntityManager em = threadLocalEntityManager.get();
        if (!em.isOpen()) {
            logger.warn("EntityManager was closed. Creating a new one.");
            threadLocalEntityManager.set(entityManagerFactory.createEntityManager());
        }
        return threadLocalEntityManager.get();
    }

    public void close() {
        EntityManager em = threadLocalEntityManager.get();
        if (em != null && em.isOpen()) {
            em.close();
            threadLocalEntityManager.remove();
            logger.info("EntityManager closed successfully.");
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

