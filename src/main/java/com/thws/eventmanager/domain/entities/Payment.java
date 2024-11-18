package com.thws.eventmanager.domain.entities;

public class Payment {
    private final String paymentMethodId;
    private final long amount;
    private Status status;

    public Payment(String paymentMethodId, long amount) {
        this.paymentMethodId = paymentMethodId;
        this.amount = amount;
        this.status = Status.OPEN;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public long getAmount() {
        return amount;
    }
}
