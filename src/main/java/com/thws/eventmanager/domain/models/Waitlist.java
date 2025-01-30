package com.thws.eventmanager.domain.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// TODO: Waitlist is falsch. eventId muss weg und nur Verweis auf ein Event
public class Waitlist implements Model {
    private Event event;
    private List<User> users;


    public Waitlist(Event event) {
        if (event == null) {
            throw new IllegalArgumentException("Event cannot be null");
        }
        this.event = event;
        this.users = new ArrayList<>();
    }

    public Waitlist(){

    }

    public Event getEvent() {
        return event;
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

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
