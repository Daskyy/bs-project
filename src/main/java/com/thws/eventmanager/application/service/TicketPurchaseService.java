package com.thws.eventmanager.application.service;

import com.thws.eventmanager.application.port.in.TicketPurchaseUseCase;
import com.thws.eventmanager.application.port.in.PaymentUseCase;
import com.thws.eventmanager.domain.service.TicketService;
import com.thws.eventmanager.domain.models.Ticket;
import com.thws.eventmanager.domain.models.Payment;
import com.thws.eventmanager.domain.models.Status;

public class TicketPurchaseService implements TicketPurchaseUseCase {

    private final TicketService ticketService;  // Service for ticket-related domain logic
    private final PaymentUseCase paymentUseCase;  // Service for payment-related logic

    public TicketPurchaseService(TicketService ticketService, PaymentUseCase paymentUseCase) {
        this.ticketService = ticketService;
        this.paymentUseCase = paymentUseCase;
    }

    // Method to handle the full process of ticket purchase (reservation + payment)
    @Override
    public boolean purchaseTicket(String userEmail, long eventId, int ticketAmount, String paymentMethodId) {
        // Step 1: Check if the event is available
        if (!ticketService.eventAvailable(eventId)) {
            throw new RuntimeException("Event not available");
        }

        // Step 2: Check if there are enough tickets available
        if (!ticketService.ticketAvailable(eventId, ticketAmount)) {
            throw new RuntimeException("Not enough tickets available");
        }

        // Step 3: Create a ticket reservation (Ticket is in PENDING status)
        Ticket ticket = ticketService.createTicket(userEmail, eventId, ticketAmount);

        // Step 4: Process the payment
        long totalAmount = ticketAmount * 100; // Assuming a fixed price per ticket for simplicity
        Payment payment = new Payment(paymentMethodId, totalAmount);

        boolean paymentProcessed = paymentUseCase.processPayment(payment);

        // Step 5: Update ticket status to COMPLETED if payment was successful
        if (paymentProcessed) {
            ticketService.updateTicketStatus(ticket, Status.COMPLETED);
            return true;
        } else {
            // Step 6: Update ticket status to FAILED if payment failed
            ticketService.updateTicketStatus(ticket, Status.FAILED);
            return false;
        }
    }
}