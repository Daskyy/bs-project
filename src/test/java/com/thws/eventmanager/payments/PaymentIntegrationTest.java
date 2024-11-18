package com.thws.eventmanager.payments;

import com.stripe.param.treasury.DebitReversalListParams;
import com.thws.eventmanager.application.PaymentUseCase;
import com.thws.eventmanager.configuration.StripeConfiguration;
import com.thws.eventmanager.domain.entities.Payment;
import com.thws.eventmanager.adapter.StripePaymentService;
import com.thws.eventmanager.domain.entities.Status;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

/*
    WARNING:
    THIS TEST CLASS WILL ACTUALLY HIT THE STRIPE API AND CREATE PAYMENTS.
    FOR JUST TESTING PURPOSES, PLEASE USE THE MOCK TESTS INSTEAD.
*/


@SpringBootTest(classes = {
        PaymentUseCase.class,
        StripePaymentService.class,
        StripeConfiguration.class
})
@TestPropertySource(locations = "classpath:application-test.properties")
public class PaymentIntegrationTest {

    @Autowired
    private PaymentUseCase paymentUseCase;

    @BeforeAll
    static void loadDotenv() {
        Dotenv dotenv = Dotenv.load();
        System.setProperty("STRIPE_API_KEY_TEST", dotenv.get("STRIPE_API_KEY_TEST"));
    }

    @Test
    void testProcessPaymentWithTestCard() {
        Payment payment = new Payment("pm_card_visa", 2000L); // 20.00 EUR
        boolean result = paymentUseCase.executePayment(payment);
        assertTrue(result);
        assertEquals(Status.COMPLETED, payment.getStatus());
    }

    @Test
    void testOpenPaymentCreation() {
        Payment payment = new Payment(null, 1500L); // 15.00 EUR
        boolean result = paymentUseCase.createOpenPayment(payment);
        assertTrue(result);
        assertEquals(Status.OPEN, payment.getStatus());
    }

    @Test
    void testFailedPaymentWithDeclinedCard() {
        Payment payment = new Payment("pm_card_chargeDeclined", 2000L);
        boolean result = paymentUseCase.executePayment(payment);
        assertFalse(result);
        assertEquals(Status.FAILED, payment.getStatus());
    }

    @Test
    void testCreateFailedPaymentFlow() {
        Payment payment = new Payment("pm_card_visa", 999999999L); // Very large amount to trigger failure
        boolean result = paymentUseCase.createFailedPayment(payment);
        assertFalse(result);
        assertEquals(Status.FAILED, payment.getStatus());
    }
}