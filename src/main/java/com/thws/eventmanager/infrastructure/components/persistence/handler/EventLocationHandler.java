package com.thws.eventmanager.infrastructure.components.persistence.handler;

import com.thws.eventmanager.domain.port.out.EventLocationRepository;
import com.thws.eventmanager.domain.models.EventLocation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class EventLocationHandler implements EventLocationRepository {
    private static final String PERSISTENCE_UNIT_NAME = "eventmanager";
    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public EventLocationHandler() {
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void saveEventLocation(EventLocation event) {
        entityManager.getTransaction().begin();
        entityManager.persist(event);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<EventLocation> getAllEventLocations() {
        return entityManager.createQuery("FROM EventLocationEntity ", EventLocation.class).getResultList();
    }

    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
