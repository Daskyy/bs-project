package com.thws.eventmanager.domain.service;

import com.thws.eventmanager.domain.models.Ticket;
import com.thws.eventmanager.domain.models.Event;
import com.thws.eventmanager.domain.models.Status;
import com.thws.eventmanager.infrastructure.adapter.persistence.dbHandler;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    private final dbHandler dbHandler;  // Direct interaction with dbHandler

    public TicketService(dbHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    // Domain logic to check if the event is available
    public boolean eventAvailable(long eventId) {
        Event event = dbHandler.getEventById(eventId);
        return event != null && event.isAvailable();
    }

    // Domain logic to check if tickets are available for the event
    public boolean ticketAvailable(long eventId, int ticketAmount) {
        return dbHandler.ticketsAvailable(eventId);
    }

    // Domain logic to create a ticket using the constructor you provided
    public Ticket createTicket(String userEmail, long eventId, int ticketAmount) {
        // Construct the ticket using the available data
        String ticketId = generateTicketId();  // Assuming you have a method to generate a ticket ID
        long price = 100;  // Example price per ticket (modify as needed)

        return new Ticket(String.valueOf(eventId), userEmail, ticketId, price * ticketAmount);
    }

    // Domain logic to update the status of a ticket
    public void updateTicketStatus(Ticket ticket, Status status) {
        ticket.setStatus(status);
        dbHandler.updateTicket(ticket);  // Save the ticket with updated status
    }

    // A method to generate a ticket ID (you can customize this further)
    private String generateTicketId() {
        // For simplicity, using a random UUID. You can generate IDs however you like.
        return "ticket-" + java.util.UUID.randomUUID().toString();
    }
}