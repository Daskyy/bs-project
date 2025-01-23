package com.thws.eventmanager.domain.models;

public class Ticket implements Model {
    private Event event;
    private User user;
    private double price;
    private Status paymentStatus;

    public Ticket(Event event, User user, long price) {
        this.event = event;
        this.user = user;
        this.price = price;
        this.paymentStatus = Status.OPEN;
    }

    public Ticket() {}

    public Event getEvent() {
        return event;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Status getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Status paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
