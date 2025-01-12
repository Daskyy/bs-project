package com.thws.eventmanager;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.thws.eventmanager.infrastructure.adapter.persistence.entities.EventEntity;
import com.thws.eventmanager.infrastructure.adapter.persistence.entities.TicketEntity;
import com.thws.eventmanager.domain.service.TicketPurchaseService;
import com.thws.eventmanager.domain.models.*;
import com.thws.eventmanager.adapter.port.in.PaymentUseCase;
import com.thws.eventmanager.infrastructure.adapter.persistence.dbHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


// TODO: FIX THIS GARBAGE THAT DOESNT WORK

public class TicketEntityServiceTest {

    private TicketPurchaseService ticketService = mock(TicketPurchaseService.class);
    private dbHandler dbHandler = mock(dbHandler.class);
    private PaymentUseCase paymentUseCase = mock(PaymentUseCase.class);

    private EventEntity event;
    private TicketEntity ticket;

    @BeforeEach
    public void setup() {
        // long id,String name, String description, long ticketCount, long ticketsSold, int maxTicketsPerUser, User[] artists, EventLocation location
        //event = new Event(1L, "Sample Event", "Description", 100, 50, new User[]{}, new EventLocation());  // Example event
        ticket = new TicketEntity("1", "user@example.com", "TICKET_123", 5000);  // Example ticket

        // Mock the dbHandler methods
        when(dbHandler.getEventById("1L")).thenReturn(event);
        when(dbHandler.ticketsAvailable("1L", 1)).thenReturn(true);

        // For void methods, use doNothing() or simulate appropriate behavior
        doNothing().when(dbHandler).createReservation(anyString(), anyLong(), anyInt());
        doNothing().when(dbHandler).deleteReservation(anyString(), anyLong());
        when(dbHandler.getTicketByUserEmailAndEvent(anyString(), anyLong())).thenReturn(ticket);
        doNothing().when(dbHandler).updateTicket(any(TicketEntity.class));
    }

    @Test
    public void testPurchaseTicket_Success() {
        // Mock the payment processing
        Payment payment = new Payment("pm_123", 1000);
        when(paymentUseCase.processPayment(any(Payment.class))).thenReturn(true);

        boolean result = ticketService.purchaseTicket("user@example.com", "1L", 2, "pm_123");

        assertTrue(result);
        verify(dbHandler, times(1)).createReservation(anyString(), eq(1L), eq(2));
        verify(paymentUseCase, times(1)).processPayment(any(Payment.class));
        verify(dbHandler, times(1)).updateTicket(any(TicketEntity.class));
    }

    @Test
    public void testPurchaseTicket_EventNotAvailable() {
        // Mock an unavailable event
        when(dbHandler.getEventById("1L")).thenReturn(null);

        Exception exception = assertThrows(RuntimeException.class, () ->
                ticketService.purchaseTicket("user@example.com", "1L", 2, "pm_123"));

        // TODO: better assert dont use the message
        assertEquals("Event not available", exception.getMessage());
    }

    @Test
    public void testPurchaseTicket_NotEnoughTickets() {
        // Mock insufficient tickets available
        when(dbHandler.ticketsAvailable("1L", 1)).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () ->
                ticketService.purchaseTicket("user@example.com", "1L", 2, "pm_123"));

        // TODO: better assert dont use the message
        assertEquals("Not enough tickets available", exception.getMessage());
    }

    @Test
    public void testPurchaseTicket_PaymentFailure() {
        // Mock payment failure
        when(paymentUseCase.processPayment(any(Payment.class))).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () ->
                ticketService.purchaseTicket("user@example.com", "1L", 2, "pm_123"));

        // TODO: better assert dont use the message
        assertEquals("Payment failed", exception.getMessage());
    }
}