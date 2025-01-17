package com.thws.eventmanager.domain.usecases;

import com.thws.eventmanager.domain.port.in.PaymentUseCase;
import com.thws.eventmanager.domain.models.Payment;
import com.thws.eventmanager.domain.models.Status;

public class PaymentUseCaseService implements PaymentUseCase {
    private final PaymentService paymentService;

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
