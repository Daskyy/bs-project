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
        payment.setId(entity.getId());
        return payment;
    }

    @Override
    public PaymentEntity toEntity(Payment model) {
        PaymentEntity entity = new PaymentEntity();
        entity.setStatus(model.getStatus());
        entity.setAmount(model.getAmount());
        entity.setPaymentMethodId(model.getPaymentMethodId());
//        if(model.getId() != -1) {
//            entity.setId(model.getId());
//        }

        return entity;
    }
}
