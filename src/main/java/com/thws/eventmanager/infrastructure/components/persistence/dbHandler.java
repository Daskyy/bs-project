package com.thws.eventmanager.infrastructure.components.persistence;

import com.thws.eventmanager.infrastructure.components.persistence.entities.TicketEntity;
import com.thws.eventmanager.infrastructure.components.persistence.entities.EventEntity;
import com.thws.eventmanager.infrastructure.components.persistence.entities.WaitlistEntity;
import com.thws.eventmanager.domain.usecases.EventSearchCriteria;

import java.util.List;

public class dbHandler {
    private static dbHandler Instance ;

    public static  dbHandler getInstance() {
        if (Instance == null) {
            Instance = new dbHandler();
        }
        return Instance;
    }

    public boolean checkLocationAvailability(long locationId) {


        // TODO Implement the right way to check if a location is available
        return true;
    }

    //Events filtering and getting
    public EventEntity getEventById(String eventId) {
        // TODO: Implement this method

        // Database call to get event by ID
        //return new Event(eventId, "Sample Event", "Description", 100, 50, new User[]{}, 5); // Sample event
        return null;
    }
    public List<EventEntity> findEventsByCriteria(EventSearchCriteria criteria) {
        // TODO: Implement this method

        return null;
    }
    public List<EventEntity> getAllEvents() {
        // TODO: Implement this method

        return null;
    }

    public TicketEntity getTicketByUserEmailAndEvent(String userEmail, long eventId) {
        // TODO: Implement this method

        // Database call to fetch the ticket
        return new TicketEntity("event1", userEmail, "ticket123", 1000);
    }

    public void createReservation(String userEmail, long eventId, int amount) {
        // TODO: Implement this method

        // Database call to create a reservation
    }

    public void updateTicket(TicketEntity ticketEntity) {
        // TODO: Implement this method

        // Update ticket status in database
    }

    public boolean ticketsAvailable(String eventId, int ticketAmount) {
        // TODO: Implement this method
        return true;
    }

    public void deleteReservation(String userEmail, long eventId) {
        // TODO: Implement this method
    }

    //Waitlist
    public void createReservationOnWaitlist(String userEmail, long eventId, int amount) {
        // TODO: Implement this method
    }
    public WaitlistEntity getWaitlistByEventId(String eventId){
        // TODO: Implement this method
        return null;
    }
    public void saveWaitlist(WaitlistEntity waitlistEntity){
        // TODO: Implement this method
    }
    public void deleteWaitlistFromEvent(String eventId){
        // TODO: Implement this method
    }
}