package com.thws.eventmanager.domain.models;

import java.util.List;

public class Waitlist {
    private final String eventId;
    private final List<User> users = null;


    public Waitlist(String eventId) {
        this.eventId = eventId;
    }

    public String getEventId() {
        return eventId;
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
    }
}
