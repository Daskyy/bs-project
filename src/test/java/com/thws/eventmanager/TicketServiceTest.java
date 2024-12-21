package com.thws.eventmanager;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.thws.eventmanager.application.service.TicketPurchaseService;
import com.thws.eventmanager.domain.models.Event;
import com.thws.eventmanager.domain.models.Payment;
import com.thws.eventmanager.domain.models.Ticket;
import com.thws.eventmanager.application.port.in.PaymentUseCase;
import com.thws.eventmanager.infrastructure.adapter.persistence.dbHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@SpringBootTest(classes = {
        TicketPurchaseService.class,
        dbHandler.class
})
public class TicketServiceTest {

    @Autowired
    private TicketPurchaseService ticketService;

    @MockBean
    private dbHandler dbHandler;

    @MockBean
    private PaymentUseCase paymentUseCase;

    private Event event;
    private Ticket ticket;

    @BeforeEach
    public void setup() {
        event = new Event(1L, "Event A", "Description", 100, 0, null, 5);
        ticket = new Ticket("1", "user@example.com", "TICKET_123", 5000);  // Example ticket

        // Mock the dbHandler methods
        when(dbHandler.getEventById(1L)).thenReturn(event);
        when(dbHandler.ticketsAvailable(1L)).thenReturn(true);

        // For void methods, use doNothing() or simulate appropriate behavior
        doNothing().when(dbHandler).createReservation(anyString(), anyLong(), anyInt());
        doNothing().when(dbHandler).deleteReservation(anyString(), anyLong());
        when(dbHandler.getTicketByUserEmailAndEvent(anyString(), anyLong())).thenReturn(ticket);
        doNothing().when(dbHandler).updateTicket(any(Ticket.class));
    }

    @Test
    public void testPurchaseTicket_Success() {
        // Mock the payment processing
        Payment payment = new Payment("pm_123", 1000);
        when(paymentUseCase.processPayment(any(Payment.class))).thenReturn(true);

        boolean result = ticketService.purchaseTicket("user@example.com", 1L, 2, "pm_123");

        assertTrue(result);
        verify(dbHandler, times(1)).createReservation(anyString(), eq(1L), eq(2));
        verify(paymentUseCase, times(1)).processPayment(any(Payment.class));
        verify(dbHandler, times(1)).updateTicket(any(Ticket.class));
    }

    @Test
    public void testPurchaseTicket_EventNotAvailable() {
        // Mock an unavailable event
        when(dbHandler.getEventById(1L)).thenReturn(null);

        Exception exception = assertThrows(RuntimeException.class, () ->
                ticketService.purchaseTicket("user@example.com", 1L, 2, "pm_123"));

        // TODO: better assert dont use the message
        assertEquals("Event not available", exception.getMessage());
    }

    @Test
    public void testPurchaseTicket_NotEnoughTickets() {
        // Mock insufficient tickets available
        when(dbHandler.ticketsAvailable(1L)).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () ->
                ticketService.purchaseTicket("user@example.com", 1L, 2, "pm_123"));

        // TODO: better assert dont use the message
        assertEquals("Not enough tickets available", exception.getMessage());
    }

    @Test
    public void testPurchaseTicket_PaymentFailure() {
        // Mock payment failure
        when(paymentUseCase.processPayment(any(Payment.class))).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () ->
                ticketService.purchaseTicket("user@example.com", 1L, 2, "pm_123"));

        // TODO: better assert dont use the message
        assertEquals("Payment failed", exception.getMessage());
    }
}