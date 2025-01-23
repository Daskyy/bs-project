package com.thws.eventmanager.infrastructure.components.persistence.entities;

import com.thws.eventmanager.domain.models.Event;
import com.thws.eventmanager.domain.models.Status;
import com.thws.eventmanager.domain.models.Ticket;
import jakarta.persistence.*;

// TODO: create a table for this entity
@Entity
@Table
public class TicketEntity implements PersistenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private EventEntity event;
    @OneToOne
    private UserEntity user;
    @Column(name = "price", nullable = false)
    private double price;
    @Column(name = "status", nullable = false)
    private Status paymentStatus;

    public TicketEntity() {}

    public EventEntity getEvent() {
        return event;
    }

    public void setEvent(EventEntity event) {
        this.event = event;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
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
