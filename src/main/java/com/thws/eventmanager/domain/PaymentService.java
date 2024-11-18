package com.thws.eventmanager.domain;

import com.thws.eventmanager.domain.entities.Payment;

public interface PaymentService {
    boolean processPayment(Payment payment);
    boolean createOpenPayment(Payment payment);
    boolean createFailedPayment(Payment payment);
}
