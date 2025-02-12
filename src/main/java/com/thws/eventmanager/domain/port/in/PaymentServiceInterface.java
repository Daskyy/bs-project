package com.thws.eventmanager.domain.port.in;

import com.thws.eventmanager.domain.models.Payment;
import com.thws.eventmanager.infrastructure.components.persistence.entities.PaymentEntity;

public interface PaymentServiceInterface {
    PaymentEntity createPayment(Payment payment);
}
