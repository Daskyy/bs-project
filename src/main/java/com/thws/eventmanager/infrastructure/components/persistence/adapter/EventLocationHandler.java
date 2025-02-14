package com.thws.eventmanager.infrastructure.components.persistence.adapter;

import com.thws.eventmanager.infrastructure.components.persistence.entities.EventLocationEntity;

public class EventLocationHandler extends GenericPersistenceAdapter<EventLocationEntity, Long> {
    public EventLocationHandler() {
        super(EventLocationEntity.class);
    }
}
