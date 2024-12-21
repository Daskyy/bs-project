package com.thws.eventmanager.infrastructure.adapter.persistence;

import com.thws.eventmanager.application.port.out.EventRepository;
import com.thws.eventmanager.domain.models.Event;
import com.thws.eventmanager.domain.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class EventHandler implements EventRepository {
    private static final String PERSISTENCE_UNIT_NAME = "eventmanager";
    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public EventHandler() {
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void saveEvent(Event event) {
        entityManager.getTransaction().begin();
        entityManager.persist(event);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Event> getAllEvents() {
        return entityManager.createQuery("FROM Event", Event.class).getResultList();
    }

    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
