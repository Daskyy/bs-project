package com.thws.eventmanager.domain.port.in;

import com.stripe.model.PaymentIntent;
import com.stripe.model.Refund;
import com.thws.eventmanager.domain.models.Payment;

public interface PaymentUseCase {
    PaymentIntent processPayment(Payment payment);
    PaymentIntent createOpenPayment(Payment payment);
    PaymentIntent createFailedPayment(Payment payment);

    Refund refundPayment(Payment payment, long refundAmount);
}
