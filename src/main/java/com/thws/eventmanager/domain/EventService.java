package com.thws.eventmanager.domain;

import com.thws.eventmanager.domain.models.*;
import com.thws.eventmanager.adapter.DBAdapter;

public class EventService implements EventServiceInterface {
    public boolean createEvent(User artist, EventLocation location) {
        if(artist.getPermission() != Permission.ARTIST) {
            throw new RuntimeException("Only artists can create events"); //hier vielleicht eher eine neue Exception-Klasse erstellen?!
        } else {
            if(!DBAdapter.getInstance().checkLocationAvailability(location.getId())) {
                System.out.println("Location not available"); //unnötig?
                return false;
            }
           Event newEvent= new Event();
           newEvent.setLocation(location);
           newEvent.addArtists(artist);
           return true;
        }
    }
}
