package com.thws.eventmanager.Unit.Infrastructure.persistence.mapper;

import com.thws.eventmanager.domain.models.*;
import com.thws.eventmanager.infrastructure.components.persistence.entities.*;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.TicketMapper;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicketMapperTest {
    private final TicketMapper mapper = new TicketMapper();

    @Test
    void toModelTest() {
        //fill attributes
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setName("John Doe");

        EventEntity eventEntity = new EventEntity();
        eventEntity.setId(10L);
        eventEntity.setName("Concert");
        eventEntity.setStartDate(LocalDateTime.of(2025, 5, 10, 20, 0));
        EventLocationEntity eventLocation = new EventLocationEntity();
        eventLocation.setAddress(new AddressEntity());
        eventEntity.setLocation(eventLocation);


        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setId(100L);
        paymentEntity.setAmount(5000L);

        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setId(5L);
        ticketEntity.setUser(userEntity);
        ticketEntity.setEvent(eventEntity);
        ticketEntity.setPrice(5000L);
        ticketEntity.setPayment(paymentEntity);

        Ticket ticket = mapper.toModel(ticketEntity);

        assertEquals(5L, ticket.getId());
        assertEquals(1L, ticket.getUser().getId());
        assertEquals(10L, ticket.getEvent().getId());
        assertEquals(100L, ticket.getPayment().getId());
    }
    @Test
    void toEntityTest() {
        //fill attributes
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");

        Event event = new Event();
        EventLocation eventLocation = new EventLocation();
        eventLocation.setAddress(new Address());
        event.setLocation(eventLocation);
        event.setId(10L);
        event.setName("Concert");
        event.setStartDate(LocalDateTime.of(2025, 5, 10, 20, 0));

        Payment payment = new Payment();
        payment.setId(100L);
        payment.setAmount(5000L);
        payment.setPaymentMethodId("PaymentMethod");

        Ticket ticket = new Ticket();
        ticket.setId(5L);
        ticket.setUser(user);
        ticket.setEvent(event);
        ticket.setPayment(payment);

        TicketEntity entity = mapper.toEntity(ticket);

        assertEquals("Concert", entity.getEvent().getName());
        assertEquals("John Doe", entity.getUser().getName());
        assertEquals(5000L, entity.getPrice());
        assertEquals("PaymentMethod", entity.getPayment().getPaymentMethodId());
    }
}
