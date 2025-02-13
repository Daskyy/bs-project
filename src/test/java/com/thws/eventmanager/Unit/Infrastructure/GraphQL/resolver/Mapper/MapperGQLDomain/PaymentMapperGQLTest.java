package com.thws.eventmanager.Unit.Infrastructure.GraphQL.resolver.Mapper.MapperGQLDomain;

import com.thws.eventmanager.domain.models.Payment;
import com.thws.eventmanager.domain.models.Status;
import com.thws.eventmanager.infrastructure.GraphQL.Models.PaymentGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Models.StatusGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain.PaymentMapperGQL;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentMapperGQLTest {
    private final PaymentMapperGQL paymentMapperGQL = new PaymentMapperGQL();

    @Test
    void testToModel() {
        // Create a sample PaymentGQL object
        PaymentGQL paymentGQL = new PaymentGQL();
        paymentGQL.setId("1");
        paymentGQL.setPaymentMethodId("123");
        paymentGQL.setAmount(100);
        paymentGQL.setStatus(StatusGQL.COMPLETED);
        paymentGQL.setPaymentIntentId("intent123");

        Payment payment = paymentMapperGQL.toModel(paymentGQL);

        // Check conversion
        assertNotNull(payment);
        assertEquals("123", payment.getPaymentMethodId());
        assertEquals(100L, payment.getAmount());
        assertEquals(Status.COMPLETED, payment.getStatus());
        assertEquals("intent123", payment.getPaymentIntentId());
        assertEquals(1L, payment.getId());
    }

    @Test
    void testToModelGQL() {
        // Create a sample Payment object
        Payment payment = new Payment();
        payment.setId(1L);
        payment.setPaymentMethodId("credit_card");
        payment.setAmount(100L);
        payment.setStatus(Status.COMPLETED);
        payment.setPaymentIntentId("intent123");

        PaymentGQL paymentGQL = paymentMapperGQL.toModelGQL(payment);

        // Check conversion
        assertNotNull(paymentGQL);
        assertEquals("credit_card", paymentGQL.getPaymentMethodId());
        assertEquals(100, paymentGQL.getAmount());
        assertEquals(StatusGQL.COMPLETED, paymentGQL.getStatus());
        assertEquals("intent123", paymentGQL.getPaymentIntentId());
        assertEquals("1", paymentGQL.getId());
    }

    @Test
    void testToModel_Null() {
        Payment payment = paymentMapperGQL.toModel(null);
        assertNull(payment);
    }

    @Test
    void testToModelGQL_Null() {
        PaymentGQL paymentGQL = paymentMapperGQL.toModelGQL(null);
        assertNull(paymentGQL);
    }

    @Test
    void testToModelGQL_withSpecialId() {
        Payment payment = new Payment();
        payment.setId(-1L);
        payment.setPaymentMethodId("credit_card");
        payment.setAmount(100);
        payment.setStatus(Status.COMPLETED);
        payment.setPaymentIntentId("intent123");

        PaymentGQL paymentGQL = paymentMapperGQL.toModelGQL(payment);
        assertNotNull(paymentGQL);
        assertEquals("-1", paymentGQL.getId());
    }
}