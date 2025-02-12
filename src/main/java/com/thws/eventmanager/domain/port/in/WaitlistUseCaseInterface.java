package com.thws.eventmanager.domain.port.in;

import com.thws.eventmanager.domain.models.User;

import java.util.List;
import java.util.Optional;

public interface WaitlistUseCaseInterface {
    void addUserToWaitlist(String eventId, User user);
    Optional<User> processNextUser(String eventId);
    List<User> getUsersInWaitlist(String eventId);
    void deleteWaitlist(String eventId);
}
