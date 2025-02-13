package com.thws.eventmanager.Integration.Payment.paymentgateway;

import com.stripe.model.PaymentIntent;
import com.thws.eventmanager.domain.services.other.PaymentUseCaseService;
import com.thws.eventmanager.domain.models.Payment;
import com.thws.eventmanager.infrastructure.components.paymentgateway.StripePaymentService;
import com.thws.eventmanager.domain.models.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/*
    THIS TEST CLASS WILL NOT HIT THE STRIPE API DUE TO MOCKING.
    IT USES MOCKS TO SIMULATE STRIPE PAYMENT INTERACTIONS.
*/

public class MockPaymentIntegrationTest {
    private StripePaymentService stripePaymentService;
    private PaymentUseCaseService paymentUseCaseService;

    @BeforeEach
    void setUp() {
        stripePaymentService = mock(StripePaymentService.class);
        paymentUseCaseService = new PaymentUseCaseService(stripePaymentService);
    }

    @Test
    void testProcessPaymentWithTestCard() {
        Payment payment = new Payment("pm_card_visa", 2000L);

        PaymentIntent mockPaymentIntent = mock(PaymentIntent.class);
        when(mockPaymentIntent.getId()).thenReturn("pi_test_123");
        when(mockPaymentIntent.getStatus()).thenReturn("succeeded");
        when(stripePaymentService.processPayment(payment)).thenReturn(mockPaymentIntent);

        PaymentIntent result = paymentUseCaseService.processPayment(payment);

        assertNotNull(result);
        assertEquals("pi_test_123", result.getId());
        assertEquals(Status.COMPLETED, payment.getStatus());

        verify(stripePaymentService).processPayment(payment);
    }

    @Test
    void testOpenPaymentCreation() {
        Payment payment = new Payment(null, 1500L);

        PaymentIntent mockPaymentIntent = mock(PaymentIntent.class);
        when(mockPaymentIntent.getId()).thenReturn("pi_open_456");
        when(mockPaymentIntent.getStatus()).thenReturn("requires_payment_method");
        when(stripePaymentService.createOpenPayment(payment)).thenReturn(mockPaymentIntent);

        PaymentIntent result = paymentUseCaseService.createOpenPayment(payment);

        assertNotNull(result);
        assertEquals("pi_open_456", result.getId());
        assertEquals(Status.OPEN, payment.getStatus());

        verify(stripePaymentService).createOpenPayment(payment);
    }

    @Test
    void testFailedPaymentWithDeclinedCard() {
        Payment payment = new Payment("pm_card_chargeDeclined", 2000L);

        PaymentIntent mockPaymentIntent = mock(PaymentIntent.class);
        when(mockPaymentIntent.getId()).thenReturn("pi_failed_789");
        when(mockPaymentIntent.getStatus()).thenReturn("failed");
        when(stripePaymentService.processPayment(payment)).thenReturn(mockPaymentIntent);

        PaymentIntent result = paymentUseCaseService.processPayment(payment);

        assertNotNull(result);
        assertEquals("pi_failed_789", result.getId());
        assertEquals(Status.FAILED, payment.getStatus());

        verify(stripePaymentService).processPayment(payment);
    }

    @Test
    void testCreateFailedPaymentFlow() {
        Payment payment = new Payment("pm_card_visa", 999999999L); // Very large amount to trigger failure

        PaymentIntent mockPaymentIntent = mock(PaymentIntent.class);
        when(mockPaymentIntent.getId()).thenReturn("pi_failed_999");
        when(mockPaymentIntent.getStatus()).thenReturn("failed");
        when(stripePaymentService.createFailedPayment(payment)).thenReturn(mockPaymentIntent);

        PaymentIntent result = paymentUseCaseService.createFailedPayment(payment);

        assertNotNull(result);
        assertEquals("pi_failed_999", result.getId());
        assertEquals(Status.FAILED, payment.getStatus());

        verify(stripePaymentService).createFailedPayment(payment);
    }
}
