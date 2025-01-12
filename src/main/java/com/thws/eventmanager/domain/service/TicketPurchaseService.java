package com.thws.eventmanager.domain.service;

import com.thws.eventmanager.application.port.in.TicketPurchaseUseCase;
import com.thws.eventmanager.application.port.in.PaymentUseCase;
import com.thws.eventmanager.application.database.service.TicketService;
import com.thws.eventmanager.domain.models.Ticket;
import com.thws.eventmanager.domain.models.Payment;
import com.thws.eventmanager.domain.models.Status;

public class TicketPurchaseService implements TicketPurchaseUseCase {

    private final TicketService ticketService;  // Service for ticket-related domain logic
    private final PaymentUseCase paymentUseCase;  // Service for payment-related logic

    private final int TICKETPRICE = 100; // Assuming a fixed price per ticket for simplicity

    public TicketPurchaseService(TicketService ticketService, PaymentUseCase paymentUseCase) {
        this.ticketService = ticketService;
        this.paymentUseCase = paymentUseCase;
    }

    // A method to generate a ticket ID (you can customize this further)
    private String generateTicketId() {
        // For simplicity, using a random UUID. You can generate IDs however you like.
        return "ticket-" + java.util.UUID.randomUUID().toString();
    }

    // Method to handle the full process of ticket purchase (reservation + payment)
    @Override
    public boolean purchaseTicket(String userEmail, String eventId, int ticketAmount, String paymentMethodId) {
        // Step 1: Check if the event is available
        if (!ticketService.eventAvailable(eventId)) {
            throw new RuntimeException("Event not available");
        }

        // Step 2: Check if there are enough tickets available
        if (!ticketService.ticketAvailable(eventId, ticketAmount)) {
            throw new RuntimeException("Not enough tickets available");
        }

        // TODO: get ticket price
        long ticketPrice = TICKETPRICE;

        // Step 3: Create a ticket reservation (Ticket is in PENDING status)
        // TODO: there must not be ticket id duplicats
        Ticket[] tickets = new Ticket[ticketAmount];
        for (int i = 0; i < ticketAmount; i++) {
            tickets[i] = new Ticket(generateTicketId(), userEmail, eventId, ticketPrice);
            ticketService.createTicket(tickets[i]);
        }


        // Step 4: Process the payment
        long totalAmount = ticketAmount * TICKETPRICE;
        Payment payment = new Payment(paymentMethodId, totalAmount);

        boolean paymentProcessed = paymentUseCase.processPayment(payment);

        // Step 5: Update ticket status to COMPLETED if payment was successful
        boolean successful = true;
        for (int i = 0; i < ticketAmount; i++) {
            if (paymentProcessed) {
                ticketService.updateTicketStatus(tickets[i], Status.COMPLETED);
            } else {
                // Step 6: Update ticket status to FAILED if payment failed
                ticketService.updateTicketStatus(tickets[i], Status.FAILED);
                successful = false;
            }
        }
        return successful;
    }
}