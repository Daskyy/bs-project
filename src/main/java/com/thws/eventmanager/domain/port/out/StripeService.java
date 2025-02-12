package com.thws.eventmanager.domain.port.out;

import com.stripe.model.PaymentIntent;
import com.stripe.model.Refund;
import com.thws.eventmanager.domain.models.Payment;

public interface StripeService {
    PaymentIntent processPayment(Payment payment);
    PaymentIntent createOpenPayment(Payment payment);
    PaymentIntent createFailedPayment(Payment payment);

    Refund processRefund(String paymentIntentId, long refundAmount);
}
