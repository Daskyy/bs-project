package com.thws.eventmanager.domain.service;

import com.thws.eventmanager.domain.EventServiceInterface;
import com.thws.eventmanager.domain.models.*;
import com.thws.eventmanager.infrastructure.adapter.persistence.dbHandler;

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
