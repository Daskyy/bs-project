package com.thws.eventmanager.application.database.service;

import com.thws.eventmanager.application.database.entities.EventEntity;
import com.thws.eventmanager.application.database.port.TicketServiceInterface;
import com.thws.eventmanager.domain.models.Status;
import com.thws.eventmanager.domain.models.Ticket;
import com.thws.eventmanager.application.database.entities.TicketEntity;
import com.thws.eventmanager.infrastructure.adapter.persistence.dbHandler;

public class TicketService implements TicketServiceInterface {

    private final dbHandler dbHandler;

    public TicketService(dbHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    @Override
    public boolean eventAvailable(String eventId) {
        EventEntity eventEntity = dbHandler.getEventById(eventId);
        return eventEntity != null && eventEntity.isAvailable();
    }

    @Override
    public boolean ticketAvailable(String eventId, int ticketAmount) {
        return dbHandler.ticketsAvailable(eventId, ticketAmount);
    }

    // TODO: sould bring a TicketEntity from the database
    public TicketEntity getTicket(String ticketId) {
        return new TicketEntity(null, null, null, 0);
    }

    @Override
    public void updateTicketStatus(Ticket ticket, Status status) {
        ticket.setStatus(status);
        dbHandler.updateTicket(getTicket(ticket.getTicketId()));  // Save the ticket with updated status
    }

    @Override
    public void createTicket(Ticket ticket) {
        new TicketEntity(ticket);
    }
}
