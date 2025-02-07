package com.thws.eventmanager.Unit.domain.models;

import com.thws.eventmanager.domain.models.Payment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    @Test
    void setId(){
        Payment payment = new Payment();
        payment.setId(100L);
        assertEquals(100L, payment.getId());
    }
    @Test
    void setIdAgain(){
        Payment payment = new Payment();
        payment.setId(100L);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()->{payment.setId(200L);});
        assertEquals("ID is already set", exception.getMessage());
    }
}