package com.thws.eventmanager.domain.usecases;

import com.thws.eventmanager.domain.port.out.EventServiceInterface;
import com.thws.eventmanager.domain.models.*;

public class EventService implements EventServiceInterface {
    @Override
    public boolean createEvent(User artist, EventLocation location) {
        if(artist.getPermission() == Permission.CUSTOMER) {
            throw new RuntimeException("Only artists can create events"); //hier vielleicht eher eine neue Exception-Klasse erstellen?!
        } else {
            if(true) {
                System.out.println("Location not available"); //unnötig?
                return false;
            }
           Event newEvent= new Event();
           newEvent.setLocation(location);
           //newEvent.addArtists(artist);
           return true;

        }
    }
}
