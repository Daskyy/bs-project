package com.thws.eventmanager.domain.services.db;

import com.thws.eventmanager.domain.models.Payment;
import com.thws.eventmanager.domain.port.in.PaymentServiceInterface;
import com.thws.eventmanager.infrastructure.components.persistence.adapter.PaymentHandler;
import com.thws.eventmanager.infrastructure.components.persistence.entities.PaymentEntity;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.PaymentMapper;

public class PaymentService implements PaymentServiceInterface {
    private final PaymentMapper paymentMapper = new PaymentMapper();
    @Override
    public PaymentEntity createPayment(Payment payment) {
        try(PaymentHandler paymentHandler = new PaymentHandler()){
            return paymentHandler.save(paymentMapper.toEntity(payment));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating payment");
        }
    }
}
