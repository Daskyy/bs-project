package com.thws.eventmanager.domain.entities;

public class Payment {
    private final String paymentMethodId;
    private final long amount;

    public Payment(String paymentMethodId, long amount) {
        this.paymentMethodId = paymentMethodId;
        this.amount = amount;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public long getAmount() {
        return amount;
    }
}
