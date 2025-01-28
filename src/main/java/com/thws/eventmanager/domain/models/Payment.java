package com.thws.eventmanager.domain.models;

public class Payment implements Model {
    private String paymentMethodId;
    private long amount;
    private Status status;

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
}
