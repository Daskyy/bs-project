package com.thws.eventmanager.infrastructure.components.persistence.adapter;

import com.thws.eventmanager.infrastructure.components.persistence.entities.EventEntity;
import jakarta.persistence.EntityManager;

public class EventHandler extends GenericPersistenceAdapter<EventEntity, Long> {

    public EventHandler() {
        super(EventEntity.class);
    }
}
