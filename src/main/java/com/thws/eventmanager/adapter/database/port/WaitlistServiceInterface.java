package com.thws.eventmanager.adapter.database.port;

import com.thws.eventmanager.infrastructure.adapter.persistence.entities.UserEntity;
import com.thws.eventmanager.infrastructure.adapter.persistence.entities.WaitlistEntity;

import java.util.List;
import java.util.Optional;

public interface WaitlistServiceInterface {
    public WaitlistEntity getWaitlist(String eventId);
    public void addUserToWaitlist(String eventId, UserEntity userEntity);
    public Optional<UserEntity> processNextUser(String eventId);
    public List<UserEntity> getUsersInWaitlist(String eventId);
    public void deleteWaitlist(String eventId);
}
