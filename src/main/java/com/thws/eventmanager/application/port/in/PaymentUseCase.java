package com.thws.eventmanager.application.port.in;

import com.thws.eventmanager.domain.models.Payment;

public interface PaymentUseCase {
    boolean processPayment(Payment payment);
    boolean createOpenPayment(Payment payment);
    boolean createFailedPayment(Payment payment);
}
