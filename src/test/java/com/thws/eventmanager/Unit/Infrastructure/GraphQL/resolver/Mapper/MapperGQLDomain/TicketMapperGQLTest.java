package com.thws.eventmanager.Unit.Infrastructure.GraphQL.resolver.Mapper.MapperGQLDomain;

import com.thws.eventmanager.domain.models.*;
import com.thws.eventmanager.infrastructure.GraphQL.Models.*;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain.TicketMapperGQL;
import org.hibernate.event.spi.EventManager;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TicketMapperGQLTest {
    private final TicketMapperGQL ticketMapperGQL = new TicketMapperGQL();

    private EventGQL createValidEventGQL() {
        EventGQL eventGQL = new EventGQL();
        eventGQL.setId("1");
        UserGQL artist1 = new UserGQL();
        artist1.setId("1");
        artist1.setPermission(PermissionGQL.ARTIST);
        UserGQL user = new UserGQL();
        user.setId("3");
        user.setPermission(PermissionGQL.CUSTOMER);
        eventGQL.setArtists(List.of(artist1));
        eventGQL.setBlockList(List.of(user));
        eventGQL.setTicketPrice(20L);
        eventGQL.setStartDate("2025-02-13T10:00:00");
        eventGQL.setEndDate("2025-02-13T12:00:00");
        TicketGQL ticketGQL = new TicketGQL();
        ticketGQL.setId("1");
        ticketGQL.setUser(user);
        ticketGQL.setEvent(eventGQL);
        ticketGQL.setPayment(new PaymentGQL());

        return eventGQL;
    }
    private UserGQL createValidUserGQL() {
        UserGQL user = new UserGQL();
        user.setId("3");
        user.setPermission(PermissionGQL.CUSTOMER);
        return user;
    }

    @Test
    void testToModelGQL() {
        // Create a Ticket object with sample data
        Event event = new Event();
        event.setId(1L);
        User user = new User();
        user.setPermission(Permission.CUSTOMER);
        event.setStartDate(LocalDateTime.parse("2025-02-13T10:00:00"));
        event.setEndDate(LocalDateTime.parse("2025-02-13T12:00:00"));
        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setEvent(event);
        ticket.setPayment(new Payment());
        ticket.setId(1L);

        TicketGQL ticketGQL = ticketMapperGQL.toModelGQL(ticket);

        // Verify conversion
        assertNotNull(ticketGQL);
        assertEquals("1", ticketGQL.getId());
        assertNotNull(ticketGQL.getEvent());
        assertNotNull(ticketGQL.getUser());
        assertNotNull(ticketGQL.getPayment());
    }

    @Test
    void testToModel() {
        // Create a TicketGQL object with sample data
        EventGQL eventGQL = createValidEventGQL();
        UserGQL user = createValidUserGQL();
        TicketGQL ticketGQL = new TicketGQL();
        ticketGQL.setId("1");
        ticketGQL.setUser(user);
        ticketGQL.setEvent(eventGQL);
        ticketGQL.setPayment(new PaymentGQL());

        Ticket ticket = ticketMapperGQL.toModel(ticketGQL);

        // Verify conversion
        assertNotNull(ticket);
        assertEquals(1L, ticket.getId());
        assertNotNull(ticket.getEvent());
        assertNotNull(ticket.getUser());
        assertNotNull(ticket.getPayment());
    }

    @Test
    void testToModel_withNullInput() {
        // Test with null input
        Ticket ticket = ticketMapperGQL.toModel(null);
        assertNull(ticket);
    }

    @Test
    void testToModelGQL_withNullInput() {
        // Test with null input
        TicketGQL ticketGQL = ticketMapperGQL.toModelGQL(null);
        assertNull(ticketGQL);
    }

    @Test
    void testToModel_withSpecialId() {
        // Test with a special id value (-1) for Ticket
        EventGQL eventGQL = createValidEventGQL();
        UserGQL user = createValidUserGQL();

        TicketGQL ticketGQL = new TicketGQL();
        ticketGQL.setId("-1");
        ticketGQL.setEvent(eventGQL);
        ticketGQL.setUser(user);
        ticketGQL.setPayment(new PaymentGQL());

        Ticket ticket = ticketMapperGQL.toModel(ticketGQL);
        assertNotNull(ticket);
        assertEquals(-1L, ticket.getId());
    }
}