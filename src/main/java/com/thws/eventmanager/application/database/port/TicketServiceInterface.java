package com.thws.eventmanager.application.database.port;

import com.thws.eventmanager.domain.models.Status;
import com.thws.eventmanager.application.database.entities.TicketEntity;
import com.thws.eventmanager.domain.models.Ticket;

public interface TicketServiceInterface {
    public boolean eventAvailable(long eventId);
    public boolean ticketAvailable(long eventId, int ticketAmount);
    public void updateTicketStatus(TicketEntity ticketEntity, Status status);

    boolean eventAvailable(String eventId);

    boolean ticketAvailable(String eventId, int ticketAmount);

    void updateTicketStatus(Ticket ticket, Status status);

    public void createTicket(Ticket ticket);
    public void addWaitlistReservation(String userEmail, long eventId, int amount);
    public void cancelReservation(String userEmail, long eventId);
}
