package com.thws.eventmanager.adapter.database.service;

import com.thws.eventmanager.infrastructure.adapter.persistence.entities.UserEntity;
import com.thws.eventmanager.infrastructure.adapter.persistence.entities.WaitlistEntity;
import com.thws.eventmanager.adapter.database.port.WaitlistServiceInterface;
import com.thws.eventmanager.infrastructure.adapter.persistence.dbHandler;

import java.util.List;
import java.util.Optional;

public class WaitlistService implements WaitlistServiceInterface {
    private final dbHandler dbHandler;

    public WaitlistService(dbHandler dbHandler){this.dbHandler = dbHandler; }

    @Override
    public WaitlistEntity getWaitlist(String eventId) {
        return dbHandler.getWaitlistByEventId(eventId);
    }

    @Override
    public void addUserToWaitlist(String eventId, UserEntity userEntity) {
        //get the actual waitlist from the database
        WaitlistEntity waitlistEntity = dbHandler.getWaitlistByEventId(eventId);

        if (waitlistEntity == null) {
            waitlistEntity = new WaitlistEntity(eventId);
        }

        waitlistEntity.addUser(userEntity);
        dbHandler.saveWaitlist(waitlistEntity);
    }

    @Override
    public Optional<UserEntity> processNextUser(String eventId) {
        WaitlistEntity waitlistEntity = dbHandler.getWaitlistByEventId(eventId);

        if (waitlistEntity != null && !waitlistEntity.getUsers().isEmpty()) {

            UserEntity nextUserEntity = waitlistEntity.getFirstUser();
            waitlistEntity.removeUser(nextUserEntity);
            dbHandler.saveWaitlist(waitlistEntity);

            return Optional.of(nextUserEntity);
        }
        return Optional.empty();
    }

    @Override
    public List<UserEntity> getUsersInWaitlist(String eventId) {
        WaitlistEntity waitlistEntity = dbHandler.getWaitlistByEventId(eventId);

        if (waitlistEntity != null) {
            return waitlistEntity.getUsers();
        }

        return List.of();
    }

    @Override
    public void deleteWaitlist(String eventId) {
        dbHandler.deleteWaitlistFromEvent(eventId);
    }
}
