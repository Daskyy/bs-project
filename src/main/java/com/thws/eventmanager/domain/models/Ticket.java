package com.thws.eventmanager.domain.models;

public class Ticket implements Model {
    private final String eventId;
    private final String userId;
    private final String ticketId;
    private final long price;
    private Status paymentStatus;

    public Ticket(String eventId, String userId, String ticketId, long price) {
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
