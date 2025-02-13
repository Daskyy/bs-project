/*
package com.thws.eventmanager.Unit.domain.usecases;

import com.thws.eventmanager.domain.models.Payment;
import com.thws.eventmanager.domain.models.Status;
import com.thws.eventmanager.domain.port.out.StripeService;
import com.thws.eventmanager.domain.usecases.PaymentUseCaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


TODO: FIX THIS

class PaymentUseCaseServiceTest {
    private StripeService paymentService;
    private PaymentUseCaseService paymentUseCaseService;

    @BeforeEach
    void setUp() {
        paymentService = mock(StripeService.class);
        paymentUseCaseService = new PaymentUseCaseService(paymentService);
    }

    @Test
    void testProcessPayment_Success() {
        Payment payment = new Payment();
        //when(paymentService.processPayment(payment)).thenReturn(true);

        //boolean result = paymentUseCaseService.processPayment(payment).getStatus().equals(Status.COMPLETED);

        //assertTrue(result);
        assertEquals(Status.COMPLETED, payment.getStatus());
        verify(paymentService, times(1)).processPayment(payment);
    }
    @Test
    void testProcessPayment_Failure() {
        Payment payment = new Payment();
        //when(paymentService.processPayment(payment)).thenReturn(false);

        //boolean result = paymentUseCaseService.processPayment(payment).getStatus().equals(Status.FAILED);

        //assertFalse(result);
        assertEquals(Status.FAILED, payment.getStatus());
        verify(paymentService, times(1)).processPayment(payment);
    }
    @Test
    void testCreateOpenPayment_Success() {
        Payment payment = new Payment();
        //when(paymentService.createOpenPayment(payment)).thenReturn(true);

        //boolean result = paymentUseCaseService.createOpenPayment(payment).getStatus().equals(Status.OPEN);

        //assertTrue(result);
        assertEquals(Status.OPEN, payment.getStatus());
        verify(paymentService, times(1)).createOpenPayment(payment);
    }
    @Test
    void testCreateOpenPayment_Failure() {
        Payment payment = new Payment();
        //when(paymentService.createOpenPayment(payment)).thenReturn(false);

        //boolean result = paymentUseCaseService.createOpenPayment(payment).getStatus().equals(Status.FAILED);

        //assertFalse(result);
        assertEquals(Status.FAILED, payment.getStatus());
        verify(paymentService, times(1)).createOpenPayment(payment);
    }
    @Test
    void testCreateFailedPayment() {
        Payment payment = new Payment();
       // when(paymentService.createFailedPayment(payment)).thenReturn(true);

        //boolean result = paymentUseCaseService.createFailedPayment(payment).getStatus().equals(Status.FAILED);

        //assertTrue(result);
        assertEquals(Status.FAILED, payment.getStatus());
        verify(paymentService, times(1)).createFailedPayment(payment);
    }

}*/
