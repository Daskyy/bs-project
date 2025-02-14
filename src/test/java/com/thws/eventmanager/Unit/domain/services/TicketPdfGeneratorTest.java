package com.thws.eventmanager.Unit.domain.services;

import com.thws.eventmanager.domain.models.*;
import com.thws.eventmanager.domain.services.other.TicketPdfGenerator;
import com.thws.eventmanager.infrastructure.components.persistence.entities.EventLocationEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TicketPdfGeneratorTest {

    private Ticket ticket;
    Payment payment = new Payment();
    Event event = new Event();
    User user = new User();

    @BeforeEach
    void setUp() {
        User user3 = new User();
        User artist = new User();
        artist.setName("artist");
        artist.setPermission(Permission.ARTIST);

        EventLocation location = new EventLocation();
        location.setName("Sample Venue");
        location.setAddress(new Address());

        event.setName("Sample Event");
        event.setDescription("This is a sample event");
        event.setTicketCount(1000);
        event.setTicketsSold(500);
        event.setMaxTicketsPerUser(5);
        event.setStartDate(LocalDateTime.of(2025, 12, 15, 10, 10));
        event.setEndDate(LocalDateTime.of(2025, 12, 15, 10, 15));
        event.setArtists(new ArrayList<>(List.of(artist)));
        event.setBlockList(new ArrayList<>(List.of(user3)));
        event.setLocation(location);

        payment.setId(100L);
        payment.setStatus(Status.COMPLETED);
        payment.setAmount(10000L);
        payment.setPaymentIntentId("981237398123891237");
        payment.setPaymentMethodId("card");

        ticket = new Ticket();
        ticket.setId(100L);
        ticket.setPayment(payment);
        ticket.setEvent(event);

        user.setName("John Doe");
        user.setEmail("test@test.test");
        user.setPassword("password");
        user.setPermission(Permission.CUSTOMER);

        ticket.setUser(user);
    }

    @Test
    void generateTicket_createsPdfWithCorrectFileName() throws IOException {
        String filePath = TicketPdfGenerator.generateTicket(ticket, 2, payment);
        assertTrue(new File(filePath).exists());
    }

    @Test
    void generateTicket_handlesNullTicket() {
        assertThrows(NullPointerException.class, () -> {
            TicketPdfGenerator.generateTicket(null, 2, payment);
        });
    }
}