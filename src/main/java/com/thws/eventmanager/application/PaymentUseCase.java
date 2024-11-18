package com.thws.eventmanager.application;

import com.thws.eventmanager.domain.PaymentService;
import com.thws.eventmanager.domain.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentUseCase {
    private final PaymentService paymentService;

    @Autowired
    public PaymentUseCase(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public boolean executePayment(Payment payment) {
        return paymentService.processPayment(payment);
    }

    public boolean createOpenPayment(Payment payment) {
        return paymentService.createOpenPayment(payment);
    }

    public boolean createFailedPayment(Payment payment) {
        return paymentService.createFailedPayment(payment);
    }
}
