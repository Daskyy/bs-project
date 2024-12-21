package com.thws.eventmanager.paymentgateway;

import com.stripe.Stripe;
import com.thws.eventmanager.application.service.PaymentUseCaseService;
import com.thws.eventmanager.infrastructure.adapter.paymentgateway.StripePaymentService;
import com.thws.eventmanager.domain.models.Payment;
import com.thws.eventmanager.domain.models.Status;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
    WARNING:
    THIS TEST CLASS WILL ACTUALLY HIT THE STRIPE API AND CREATE PAYMENTS.
    FOR JUST TESTING PURPOSES, PLEASE USE THE MOCK TESTS INSTEAD.
*/

public class PaymentIntegrationTest {
    private final PaymentUseCaseService paymentUseCaseService = new PaymentUseCaseService(new StripePaymentService());

    @Test
    void testProcessPaymentWithTestCard() {
        Payment payment = new Payment("pm_card_visa", 2000L); // 20.00 EUR
        boolean result = paymentUseCaseService.processPayment(payment);
        assertTrue(result);
        assertEquals(Status.COMPLETED, payment.getStatus());
    }

    @Test
    void testOpenPaymentCreation() {
        Payment payment = new Payment(null, 1500L); // 15.00 EUR
        boolean result = paymentUseCaseService.createOpenPayment(payment);
        assertTrue(result);
        assertEquals(Status.OPEN, payment.getStatus());
    }

    @Test
    void testFailedPaymentWithDeclinedCard() {
        Payment payment = new Payment("pm_card_chargeDeclined", 2000L);
        boolean result = paymentUseCaseService.processPayment(payment);
        assertFalse(result);
        assertEquals(Status.FAILED, payment.getStatus());
    }

    @Test
    void testCreateFailedPaymentFlow() {
        Payment payment = new Payment("pm_card_visa", 999999999L); // Very large amount to trigger failure
        boolean result = paymentUseCaseService.createFailedPayment(payment);
        assertFalse(result);
        assertEquals(Status.FAILED, payment.getStatus());
    }
}