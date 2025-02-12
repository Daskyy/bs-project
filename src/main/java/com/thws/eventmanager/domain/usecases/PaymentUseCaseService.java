package com.thws.eventmanager.domain.usecases;

import com.stripe.model.PaymentIntent;
import com.stripe.model.Refund;
import com.thws.eventmanager.domain.port.in.PaymentUseCase;
import com.thws.eventmanager.domain.models.Payment;
import com.thws.eventmanager.domain.models.Status;
import com.thws.eventmanager.domain.port.out.PaymentService;
import com.thws.eventmanager.infrastructure.components.paymentgateway.StripePaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaymentUseCaseService implements PaymentUseCase {
    private final PaymentService paymentService;
    private static final Logger log = LoggerFactory.getLogger(PaymentUseCaseService.class);

    public PaymentUseCaseService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public PaymentIntent processPayment(Payment payment) {
        PaymentIntent paymentIntent = paymentService.processPayment(payment);
        if (paymentIntent != null) {
            payment.setPaymentIntentId(paymentIntent.getId());
            payment.setStatus(paymentIntent.getStatus().equals("succeeded") ? Status.COMPLETED : Status.FAILED);
        } else {
            payment.setStatus(Status.FAILED);
        }
        return paymentIntent;
    }

    public PaymentIntent createOpenPayment(Payment payment) {
        PaymentIntent paymentIntent = paymentService.createOpenPayment(payment);
        if (paymentIntent != null) {
            payment.setPaymentIntentId(paymentIntent.getId());
            payment.setStatus(paymentIntent.getStatus().equals("requires_payment_method") ? Status.OPEN : Status.FAILED);
        } else {
            payment.setStatus(Status.FAILED);
        }
        return paymentIntent;
    }

    public PaymentIntent createFailedPayment(Payment payment) {
        PaymentIntent paymentIntent = paymentService.createFailedPayment(payment);
        if (paymentIntent != null) {
            payment.setPaymentIntentId(paymentIntent.getId());
            payment.setStatus(Status.FAILED);
        } else {
            payment.setStatus(Status.FAILED);
        }
        return paymentIntent;
    }

    public Refund refundPayment(Payment payment, long refundAmount) {
        if (payment.getPaymentIntentId() == null) {
            throw new IllegalArgumentException("Cannot refund a payment without a PaymentIntent ID");
        }

        Refund refund = paymentService.processRefund(payment.getPaymentIntentId(), refundAmount);

        if (refund != null && "succeeded".equals(refund.getStatus())) {
            payment.setStatus(Status.REFUNDED); // Update status
            log.info("Payment {} successfully refunded", payment.getPaymentIntentId());
        } else {
            log.warn("Refund failed for payment {}", payment.getPaymentIntentId());
        }
        return refund;
    }
}
