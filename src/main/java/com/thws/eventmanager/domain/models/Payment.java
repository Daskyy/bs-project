package com.thws.eventmanager.domain.models;

import java.util.concurrent.Future;

public class Payment implements Model {
    private String paymentMethodId;
    private long amount;
    private Status status;
    private long id = -1;

    public Payment(String paymentMethodId, long amount) {
        this.paymentMethodId = paymentMethodId;
        this.amount = amount;
        this.status = Status.OPEN;
    }

    public Payment() {}

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
