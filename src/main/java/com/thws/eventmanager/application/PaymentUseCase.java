package com.thws.eventmanager.application;

import com.thws.eventmanager.domain.PaymentService;
import com.thws.eventmanager.domain.models.Payment;
import com.thws.eventmanager.domain.models.Status;
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
