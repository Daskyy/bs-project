package com.thws.eventmanager.Integration.Payment.paymentgateway;

import com.stripe.model.PaymentIntent;
import com.thws.eventmanager.domain.services.usecases.PaymentUseCaseService;
import com.thws.eventmanager.infrastructure.components.paymentgateway.StripePaymentService;
import com.thws.eventmanager.domain.models.Payment;
import com.thws.eventmanager.domain.models.Status;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
    WARNING:
    THIS TEST CLASS WILL ACTUALLY HIT THE STRIPE API AND CREATE PAYMENTS.
    FOR TESTING PURPOSES, PLEASE USE THE MOCK TESTS INSTEAD.
*/

public class PaymentIntegrationTest {
    private final PaymentUseCaseService paymentUseCaseService = new PaymentUseCaseService(new StripePaymentService());

    @Test
    void testProcessPaymentWithTestCard() {
        Payment payment = new Payment("pm_card_visa", 2000L); // 20.00 EUR
        PaymentIntent paymentIntent = paymentUseCaseService.processPayment(payment);

        assertNotNull(paymentIntent);
        assertEquals("succeeded", paymentIntent.getStatus());
        assertEquals(Status.COMPLETED, payment.getStatus());
        assertNotNull(payment.getPaymentIntentId());
    }

    @Test
    void testOpenPaymentCreation() {
        Payment payment = new Payment(null, 1500L); // 15.00 EUR
        PaymentIntent paymentIntent = paymentUseCaseService.createOpenPayment(payment);

        assertNotNull(paymentIntent);
        assertEquals("requires_payment_method", paymentIntent.getStatus());
        assertEquals(Status.OPEN, payment.getStatus());
        assertNotNull(payment.getPaymentIntentId());
    }

    @Test
    void testFailedPaymentWithDeclinedCard() {
        Payment payment = new Payment("pm_card_chargeDeclined", 2000L);
        PaymentIntent paymentIntent = paymentUseCaseService.processPayment(payment);

        assertNotNull(paymentIntent, "PaymentIntent should not be null, even if payment failed.");
        assertEquals("failed", paymentIntent.getStatus(), "Expected PaymentIntent status to be 'failed'.");
        assertEquals(Status.FAILED, payment.getStatus(), "Payment status should be FAILED.");
        assertNotNull(payment.getPaymentIntentId(), "PaymentIntent ID should be stored in Payment.");
        assertNotEquals("pi_unknown_failed", payment.getPaymentIntentId(), "PaymentIntent ID should not be 'pi_unknown_failed'.");
    }


    @Test
    void testCreateFailedPaymentFlow() {
        Payment payment = new Payment("pm_card_visa", 999999999L); // Very large amount to trigger failure
        PaymentIntent paymentIntent = paymentUseCaseService.createFailedPayment(payment);

        assertNotNull(paymentIntent, "PaymentIntent should not be null, even if payment failed.");
        assertEquals("failed", paymentIntent.getStatus(), "Expected PaymentIntent status to be 'failed'.");
        assertEquals(Status.FAILED, payment.getStatus(), "Payment status should be FAILED.");
    }

}
