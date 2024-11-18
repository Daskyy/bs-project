package com.thws.eventmanager.payments;

import com.thws.eventmanager.application.PaymentUseCase;
import com.thws.eventmanager.configuration.StripeConfiguration;
import com.thws.eventmanager.domain.entities.Payment;
import com.thws.eventmanager.adapter.StripePaymentService;
import com.thws.eventmanager.domain.entities.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/*
    THIS TEST CLASS WILL NOT HIT THE STRIPE API DUE TO MOCKING.
    IT USES MOCKS TO SIMULATE STRIPE PAYMENT INTERACTIONS.
*/

@SpringBootTest(classes = {
        PaymentUseCase.class,
        StripeConfiguration.class
})
@TestPropertySource(locations = "classpath:application-test.properties")
public class MockPaymentIntegrationTest {

    @Autowired
    private PaymentUseCase paymentUseCase;

    @MockBean
    private StripePaymentService stripePaymentService; // Mocking StripePaymentService

    @Test
    void testProcessPaymentWithTestCard() {
        Payment payment = new Payment("pm_card_visa", 2000L);

        when(stripePaymentService.processPayment(payment)).thenReturn(true);

        boolean result = paymentUseCase.executePayment(payment);
        assertTrue(result);
        assertEquals(Status.COMPLETED, payment.getStatus());

        verify(stripePaymentService).processPayment(payment);
    }

    @Test
    void testOpenPaymentCreation() {
        Payment payment = new Payment(null, 1500L);

        when(stripePaymentService.createOpenPayment(payment)).thenReturn(true);

        boolean result = paymentUseCase.createOpenPayment(payment);
        assertTrue(result);
        assertEquals(Status.OPEN, payment.getStatus());

        verify(stripePaymentService).createOpenPayment(payment);
    }

    @Test
    void testFailedPaymentWithDeclinedCard() {
        Payment payment = new Payment("pm_card_chargeDeclined", 2000L);

        when(stripePaymentService.processPayment(payment)).thenReturn(false);

        boolean result = paymentUseCase.executePayment(payment);
        assertFalse(result);
        assertEquals(Status.FAILED, payment.getStatus());

        verify(stripePaymentService).processPayment(payment);
    }

    @Test
    void testCreateFailedPaymentFlow() {
        Payment payment = new Payment("pm_card_visa", 999999999L); // Very large amount to trigger failure

        when(stripePaymentService.createFailedPayment(payment)).thenReturn(false);

        boolean result = paymentUseCase.createFailedPayment(payment);
        assertFalse(result);
        assertEquals(Status.FAILED, payment.getStatus());

        verify(stripePaymentService).createFailedPayment(payment);
    }
}
