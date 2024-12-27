package com.thws.eventmanager.domain.service;

import com.thws.eventmanager.domain.WaitlistServiceInterface;
import com.thws.eventmanager.domain.models.User;
import com.thws.eventmanager.domain.models.Waitlist;
import com.thws.eventmanager.infrastructure.adapter.persistence.dbHandler;

import java.util.List;
import java.util.Optional;

public class WaitlistService implements WaitlistServiceInterface {
    private final dbHandler dbHandler;

    public WaitlistService(dbHandler dbHandler){this.dbHandler = dbHandler; }

    @Override
    public Waitlist getWaitlist(String eventId) {
        return dbHandler.getWaitlistByEventId(eventId);
    }

    @Override
    public void addUserToWaitlist(String eventId, User user) {
        //get the actual waitlist from the database
        Waitlist waitlist = dbHandler.getWaitlistByEventId(eventId);

        if (waitlist == null) {
            waitlist = new Waitlist(eventId);
        }

        waitlist.addUser(user);
        dbHandler.saveWaitlist(waitlist);
    }

    @Override
    public Optional<User> processNextUser(String eventId) {
        Waitlist waitlist = dbHandler.getWaitlistByEventId(eventId);

        if (waitlist != null && !waitlist.getUsers().isEmpty()) {

            User nextUser = waitlist.getFirstUser();
            waitlist.removeUser(nextUser);
            dbHandler.saveWaitlist(waitlist);

            return Optional.of(nextUser);
        }
        return Optional.empty();
    }

    @Override
    public List<User> getUsersInWaitlist(String eventId) {
        Waitlist waitlist = dbHandler.getWaitlistByEventId(eventId);

        if (waitlist != null) {
            return waitlist.getUsers();
        }

        return List.of();
    }

    @Override
    public void deleteWaitlist(String eventId) {
        dbHandler.deleteWaitlistFromEvent(eventId);
    }
}
