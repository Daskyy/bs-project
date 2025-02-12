package com.thws.eventmanager.domain.models;

public class Ticket implements Model {
    private Event event;
    private User user;
    private Payment payment;
    private long id = -1;

    public Ticket(Event event, User user, long price) {
        this.event = event;
        this.user = user;
        this.payment = null;
    }

    public Ticket() {}

    public Event getEvent() {
        return event;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        if (this.id != -1) {
            throw new IllegalArgumentException("ID is already set");
        } else {
            this.id = id;
        }
    }

}
