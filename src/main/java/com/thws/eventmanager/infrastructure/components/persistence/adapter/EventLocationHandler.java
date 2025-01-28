package com.thws.eventmanager.infrastructure.components.persistence.adapter;

import com.thws.eventmanager.infrastructure.components.persistence.entities.EventLocationEntity;
import jakarta.persistence.EntityManager;

public class EventLocationHandler extends GenericPersistenceAdapter<EventLocationEntity, Long> {
    public EventLocationHandler(EntityManager entityManager) {
        super(entityManager, EventLocationEntity.class);
    }
}
