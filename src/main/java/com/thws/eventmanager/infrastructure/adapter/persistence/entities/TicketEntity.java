package com.thws.eventmanager.infrastructure.adapter.persistence.entities;

import com.thws.eventmanager.domain.models.Status;
import com.thws.eventmanager.domain.models.Ticket;

// TODO: create a table for this entity
public class TicketEntity {
    private final String eventId;
    private final String userId;
    private final String ticketId;
    private final long price;
    private Status paymentStatus;

    public TicketEntity(Ticket ticket){
        this.eventId = ticket.getEventId();
        this.userId = ticket.getUserId();
        this.ticketId = ticket.getTicketId();
        this.price = ticket.getPrice();
    }


    public TicketEntity(String eventId, String userId, String ticketId, long price) {
        this.eventId = eventId;
        this.userId = userId;
        this.ticketId = ticketId;
        this.price = price;
        this.paymentStatus = Status.OPEN;
    }

    public Status getStatus() {
        return paymentStatus;
    }

    public void setStatus(Status status) {
        this.paymentStatus = status;
    }

    public String getEventId() {
        return eventId;
    }

    public String getUserId() {
        return userId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public long getPrice() {
        return price;
    }
}
