package com.thws.eventmanager.domain.port.in;

import com.stripe.model.PaymentIntent;
import com.thws.eventmanager.domain.models.Payment;

public interface PaymentUseCase {
    PaymentIntent processPayment(Payment payment);
    PaymentIntent createOpenPayment(Payment payment);
    PaymentIntent createFailedPayment(Payment payment);
}
