package com.thws.eventmanager.infrastructure.components.persistence.entities;

import com.thws.eventmanager.domain.models.Status;
import jakarta.persistence.*;

@Entity
@Table(name = "payments")
public class PaymentEntity implements PersistenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String paymentMethodId;

    @Column(nullable = false)
    private long amount;

    @Column(nullable = false)
    private Status status;

    public PaymentEntity() {}

    public PaymentEntity(String paymentMethodId, long amount) {
        this.paymentMethodId = paymentMethodId;
        this.amount = amount;
        this.status = Status.OPEN; // Default status is OPEN
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
