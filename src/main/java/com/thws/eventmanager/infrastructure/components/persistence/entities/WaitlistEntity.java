package com.thws.eventmanager.infrastructure.components.persistence.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WaitlistEntity implements PersistenceEntity {
    private final String eventId;
    private final List<UserEntity> userEntities;

    public WaitlistEntity(String eventId) {
        this.eventId = eventId;
        this.userEntities = new ArrayList<>();
    }

    public String getEventId() {
        return eventId;
    }

    public List<UserEntity> getUsers() {
        return Collections.unmodifiableList(userEntities);
    }

    public void addUser(UserEntity userEntity) {
        if (userEntity == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        userEntities.add(userEntity);
    }

    public boolean removeUser(UserEntity userEntity) {
        return userEntities.remove(userEntity);
    }

    public UserEntity getFirstUser() {
        return userEntities.isEmpty() ? null : userEntities.get(0);
    }
}
