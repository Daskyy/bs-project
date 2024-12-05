package com.thws.eventmanager.domain;

import com.thws.eventmanager.componentdb.DBHandler;

public class TicketService implements TicketServiceInterface {
    DBHandler dbHandler = new DBHandler();
    //change
    public boolean eventAvailable(long eventId) {
        // creates Event object
        // get number Reservations done for Event
        // get number of Event
        // compare and return result
        return false;
    }

    public boolean ticketAvailable(long eventId) {
        return dbHandler.ticketsAvailable(eventId);
    }

    public void addReservation(String userEmail, long eventId,  int amount) {
        if (eventAvailable(eventId)) {
            dbHandler.createReservation(userEmail, eventId, amount);
        } else {
            throw new RuntimeException("Event sold out");
        }
    }

    public void addWaitlistReservation(String userEmail, long eventId,  int amount) {
        dbHandler.createReservationOnWaitlist(userEmail, eventId, amount);
    }

    public void cancelReservation(String userEmail, long eventId) {
        dbHandler.deleteReservation(userEmail,eventId);
    }
}
