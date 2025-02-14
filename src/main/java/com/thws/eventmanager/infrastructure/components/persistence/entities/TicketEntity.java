package com.thws.eventmanager.infrastructure.components.persistence.entities;

import com.thws.eventmanager.domain.models.Event;
import com.thws.eventmanager.domain.models.Status;
import com.thws.eventmanager.domain.models.Ticket;
import jakarta.persistence.*;

@Entity
@Table(name = "tickets")
public class TicketEntity implements PersistenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "event_id", nullable = false)
    private EventEntity event;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE) // Allow new payments to be saved or updated
    @JoinColumn(name = "payment_id")
    private PaymentEntity payment;

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

    public PaymentEntity getPayment() {
        return payment;
    }

    public void setPayment(PaymentEntity payment) {
        this.payment = payment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
