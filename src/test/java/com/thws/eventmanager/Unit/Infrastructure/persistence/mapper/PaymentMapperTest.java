package com.thws.eventmanager.Unit.Infrastructure.persistence.mapper;

import com.thws.eventmanager.domain.models.Payment;
import com.thws.eventmanager.domain.models.Status;
import com.thws.eventmanager.infrastructure.components.persistence.entities.PaymentEntity;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.PaymentMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentMapperTest {
    private final PaymentMapper mapper = new PaymentMapper();

    @Test
    void toModelTest() {
        PaymentEntity entity = new PaymentEntity();
        entity.setId(1L);
        entity.setStatus(Status.COMPLETED);
        entity.setAmount(100L);
        entity.setPaymentMethodId("PM12345");

        Payment payment = mapper.toModel(entity);

        assertEquals(1L, payment.getId());
        assertEquals(Status.COMPLETED, payment.getStatus());
        assertEquals(100L, payment.getAmount());
        assertEquals("PM12345", payment.getPaymentMethodId());
    }

    @Test
    void toEntityTest() {
        Payment payment = new Payment();
        payment.setId(1L);
        payment.setStatus(Status.OPEN);
        payment.setAmount(100L);
        payment.setPaymentMethodId("PM12345");

        PaymentEntity entity = mapper.toEntity(payment);

        assertEquals(Status.OPEN, entity.getStatus());
        assertEquals(100L, entity.getAmount());
        assertEquals("PM12345", entity.getPaymentMethodId());
    }
}
