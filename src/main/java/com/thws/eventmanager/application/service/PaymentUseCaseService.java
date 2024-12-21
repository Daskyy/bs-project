package com.thws.eventmanager.application.service;

import com.thws.eventmanager.application.port.in.PaymentUseCase;
import com.thws.eventmanager.domain.service.PaymentService;
import com.thws.eventmanager.domain.models.Payment;
import com.thws.eventmanager.domain.models.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentUseCaseService implements PaymentUseCase {
    private final PaymentService paymentService;

    @Autowired
    public PaymentUseCaseService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public boolean processPayment(Payment payment) {
        boolean result = paymentService.processPayment(payment);
        payment.setStatus(result ? Status.COMPLETED : Status.FAILED);
        return result;
    }

    public boolean createOpenPayment(Payment payment) {
        boolean result = paymentService.createOpenPayment(payment);
        payment.setStatus(result ? Status.OPEN : Status.FAILED);
        return result;
    }

    public boolean createFailedPayment(Payment payment) {
        boolean result = paymentService.createFailedPayment(payment);
        payment.setStatus(Status.FAILED);
        return result;
    }
}
