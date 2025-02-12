package com.thws.eventmanager.infrastructure.components.persistence.adapter;

import com.thws.eventmanager.infrastructure.components.persistence.entities.TicketEntity;

public class TicketHandler extends GenericPersistenceAdapter<TicketEntity, Long> {
    public TicketHandler() {
        super(TicketEntity.class);
    }
}
