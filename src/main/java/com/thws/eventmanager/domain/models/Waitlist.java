package com.thws.eventmanager.domain.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// TODO: Waitlist is falsch. eventId muss weg und nur Veweis auf ein Event
public class Waitlist implements Model {
    private final String eventId;
    private final List<User> users;


    public Waitlist(String eventId) {
        this.eventId = eventId;
        this.users = new ArrayList<>();
    }

    public String getEventId() {
        return eventId;
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }

    public void addUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        users.add(user);
    }

    public boolean removeUser(User user) {
        return users.remove(user);
    }

    public User getFirstUser() {
        return users.isEmpty() ? null : users.getFirst();
    }
}
