package com.thws.eventmanager.domain.port.in;

import com.thws.eventmanager.domain.models.Payment;

public interface PaymentUseCase {
    boolean processPayment(Payment payment);
    boolean createOpenPayment(Payment payment);
    boolean createFailedPayment(Payment payment);
}
