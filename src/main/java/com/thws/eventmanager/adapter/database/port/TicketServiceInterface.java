package com.thws.eventmanager.adapter.database.port;

import com.thws.eventmanager.domain.models.Status;
import com.thws.eventmanager.domain.models.Ticket;

public interface TicketServiceInterface {

    boolean eventAvailable(String eventId);

    boolean ticketAvailable(String eventId, int ticketAmount);

    void updateTicketStatus(Ticket ticket, Status status);

    void createTicket(Ticket ticket);
    Ticket getTicket(String ticketId);

    void addWaitlistReservation(String userEmail, String eventId, int amount);
    void deleteTicket(String userEmail, String eventId);
}
