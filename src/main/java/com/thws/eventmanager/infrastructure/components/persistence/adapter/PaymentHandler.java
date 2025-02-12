package com.thws.eventmanager.infrastructure.components.persistence.adapter;

import com.thws.eventmanager.infrastructure.components.persistence.entities.PaymentEntity;

public class PaymentHandler extends GenericPersistenceAdapter<PaymentEntity, Long> {
    public PaymentHandler() {
        super(PaymentEntity.class);
    }
}
