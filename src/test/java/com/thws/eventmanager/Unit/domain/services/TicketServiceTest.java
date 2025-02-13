package com.thws.eventmanager.Unit.domain.services;

import com.thws.eventmanager.domain.models.Payment;
import com.thws.eventmanager.domain.models.Status;
import com.thws.eventmanager.domain.models.Ticket;
import com.thws.eventmanager.domain.services.models.TicketService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicketServiceTest {
    private final TicketService ticketService = new TicketService();

    @Test
    void validTicketTest() {
        Ticket ticket = new Ticket();
        Payment payment = new Payment();
        payment.setStatus(Status.COMPLETED);
        ticket.setPayment(payment);

        assertTrue(ticketService.validateTicket(ticket));
    }
    @Test
    void invalidOpenPaymentTicketTest() {
        Ticket ticket = new Ticket();
        Payment payment = new Payment();
        payment.setStatus(Status.OPEN);
        ticket.setPayment(payment);

        assertFalse(ticketService.validateTicket(ticket));
    }
    @Test
    void invalidFailedPaymentTicketTest() {
        Ticket ticket = new Ticket();
        Payment payment = new Payment();
        payment.setStatus(Status.FAILED);
        ticket.setPayment(payment);

        assertFalse(ticketService.validateTicket(ticket));
    }
    @Test
    void invalidNonStatusTicketTest() {
        Ticket ticket = new Ticket();
        Payment payment = new Payment();
        ticket.setPayment(payment);

        assertFalse(ticketService.validateTicket(ticket));
    }
}