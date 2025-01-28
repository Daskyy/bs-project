package com.thws.eventmanager.domain.models;

public class Ticket implements Model {
    private Event event;
    private User user;
    private double price;
    private Payment payment;

    public Ticket(Event event, User user, long price) {
        this.event = event;
        this.user = user;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
