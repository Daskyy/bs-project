package com.thws.eventmanager.infrastructure.components.persistence.mapper;

import com.thws.eventmanager.domain.models.Payment;
import com.thws.eventmanager.infrastructure.components.persistence.entities.PaymentEntity;

public class PaymentMapper extends Mapper<Payment, PaymentEntity> {

    @Override
    public Payment toModel(PaymentEntity entity) {
        Payment payment = new Payment();
        payment.setStatus(entity.getStatus());
        payment.setAmount(entity.getAmount());
        payment.setPaymentMethodId(entity.getPaymentMethodId());

        return payment;
    }

    @Override
    public PaymentEntity toEntity(Payment model) {
        PaymentEntity entity = new PaymentEntity();
        entity.setStatus(model.getStatus());
        entity.setAmount(model.getAmount());
        entity.setPaymentMethodId(model.getPaymentMethodId());

        return entity;
    }
}
