package com.thws.eventmanager.application.port.out;

import com.thws.eventmanager.domain.models.Payment;

public interface PaymentGatewayPort {
    boolean processPayment(Payment payment);
    boolean createOpenPayment(Payment payment);
    boolean createFailedPayment(Payment payment);
}
