package com.thws.eventmanager.domain.port.in;

import com.thws.eventmanager.domain.models.Ticket;
import com.thws.eventmanager.infrastructure.components.persistence.entities.TicketEntity;

public interface TicketServiceInterface {
    TicketEntity createTicket(Ticket ticket);
    boolean validateTicket(Ticket ticket);
    Ticket deleteTicket(Ticket ticket);

    TicketEntity getTicketById(long id);
}
