package com.thws.eventmanager.infrastructure.adapter.persistence;

import com.thws.eventmanager.domain.models.Ticket;
import com.thws.eventmanager.domain.models.Event;
import com.thws.eventmanager.domain.models.User;
import com.thws.eventmanager.domain.models.Waitlist;
import com.thws.eventmanager.domain.service.EventSearchCriteria;

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
    public Event getEventById(long eventId) {
        // TODO: Implement this method

        // Database call to get event by ID
        //return new Event(eventId, "Sample Event", "Description", 100, 50, new User[]{}, 5); // Sample event
        return null;
    }
    public List<Event> findEventsByCriteria(EventSearchCriteria criteria) {
        // TODO: Implement this method

        return null;
    }
    public List<Event> getAllEvents() {
        // TODO: Implement this method

        return null;
    }

    public Ticket getTicketByUserEmailAndEvent(String userEmail, long eventId) {
        // TODO: Implement this method

        // Database call to fetch the ticket
        return new Ticket("event1", userEmail, "ticket123", 1000);
    }

    public void createReservation(String userEmail, long eventId, int amount) {
        // TODO: Implement this method

        // Database call to create a reservation
    }

    public void updateTicket(Ticket ticket) {
        // TODO: Implement this method

        // Update ticket status in database
    }

    public boolean ticketsAvailable(long eventId) {
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
    public Waitlist getWaitlistByEventId(String eventId){
        // TODO: Implement this method
        return null;
    }
    public void saveWaitlist(Waitlist waitlist){
        // TODO: Implement this method
    }
    public void deleteWaitlistFromEvent(String eventId){
        // TODO: Implement this method
    }
}