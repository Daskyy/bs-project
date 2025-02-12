package com.thws.eventmanager.infrastructure.GraphQL.Models;

public class PaymentGQL implements GQLModel {
    private String paymentMethodId;
    private int amount;
    private StatusGQL status;
    private String id;
    private String paymentIntentId;


    public PaymentGQL(){}

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public StatusGQL getStatus() {
        return status;
    }

    public void setStatus(StatusGQL status) {
        this.status = status;
    }

    public String getPaymentIntentId() {
        return paymentIntentId;
    }

    public void setPaymentIntentId(String paymentIntentId) {
        this.paymentIntentId = paymentIntentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
