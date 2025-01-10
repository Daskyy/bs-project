package com.thws.eventmanager.domain;

import com.thws.eventmanager.domain.models.User;
import com.thws.eventmanager.domain.models.Waitlist;

import java.util.List;
import java.util.Optional;

public interface WaitlistServiceInterface {
    public Waitlist getWaitlist(String eventId);
    public void addUserToWaitlist(String eventId, User user);
    public Optional<User> processNextUser(String eventId);
    public List<User> getUsersInWaitlist(String eventId);
    public void deleteWaitlist(String eventId);
}
