package com.thws.eventmanager.infrastructure.GraphQL.Models;

public class TicketGQL {
    private String id;
    private EventGQL event;
    private UserGQL user;
    private float price;
    private PaymentGQL payment;

    public TicketGQL() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EventGQL getEvent() {
        return event;
    }

    public void setEvent(EventGQL event) {
        this.event = event;
    }

    public UserGQL getUser() {
        return user;
    }

    public void setUser(UserGQL user) {
        this.user = user;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public PaymentGQL getPayment() {
        return payment;
    }

    public void setPayment(PaymentGQL payment) {
        this.payment = payment;
    }
}
