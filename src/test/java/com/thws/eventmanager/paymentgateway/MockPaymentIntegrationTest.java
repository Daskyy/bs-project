package com.thws.eventmanager.paymentgateway;

import com.thws.eventmanager.domain.usecases.PaymentUseCaseService;
import com.thws.eventmanager.domain.models.Payment;
import com.thws.eventmanager.infrastructure.components.paymentgateway.StripePaymentService;
import com.thws.eventmanager.domain.models.Status;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/*
    THIS TEST CLASS WILL NOT HIT THE STRIPE API DUE TO MOCKING.
    IT USES MOCKS TO SIMULATE STRIPE PAYMENT INTERACTIONS.
*/

public class MockPaymentIntegrationTest {
    private StripePaymentService stripePaymentService = mock(StripePaymentService.class); // Mocking StripePaymentService
    private PaymentUseCaseService paymentUseCaseService = new PaymentUseCaseService(stripePaymentService);

    @Test
    void testProcessPaymentWithTestCard() {
        Payment payment = new Payment("pm_card_visa", 2000L);

        when(stripePaymentService.processPayment(payment)).thenReturn(true);

        boolean result = paymentUseCaseService.processPayment(payment);
        assertTrue(result);
        assertEquals(Status.COMPLETED, payment.getStatus());

        verify(stripePaymentService).processPayment(payment);
    }

    @Test
    void testOpenPaymentCreation() {
        Payment payment = new Payment(null, 1500L);

        when(stripePaymentService.createOpenPayment(payment)).thenReturn(true);

        boolean result = paymentUseCaseService.createOpenPayment(payment);
        assertTrue(result);
        assertEquals(Status.OPEN, payment.getStatus());

        verify(stripePaymentService).createOpenPayment(payment);
    }

    @Test
    void testFailedPaymentWithDeclinedCard() {
        Payment payment = new Payment("pm_card_chargeDeclined", 2000L);

        when(stripePaymentService.processPayment(payment)).thenReturn(false);

        boolean result = paymentUseCaseService.processPayment(payment);
        assertFalse(result);
        assertEquals(Status.FAILED, payment.getStatus());

        verify(stripePaymentService).processPayment(payment);
    }

    @Test
    void testCreateFailedPaymentFlow() {
        Payment payment = new Payment("pm_card_visa", 999999999L); // Very large amount to trigger failure

        when(stripePaymentService.createFailedPayment(payment)).thenReturn(false);

        boolean result = paymentUseCaseService.createFailedPayment(payment);
        assertFalse(result);
        assertEquals(Status.FAILED, payment.getStatus());

        verify(stripePaymentService).createFailedPayment(payment);
    }
}
