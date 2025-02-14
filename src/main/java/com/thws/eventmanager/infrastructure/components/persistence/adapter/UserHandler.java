package com.thws.eventmanager.infrastructure.components.persistence.adapter;

import com.thws.eventmanager.infrastructure.components.persistence.entities.UserEntity;

public class UserHandler extends GenericPersistenceAdapter<UserEntity, Long> {
    public UserHandler() {
        super(UserEntity.class);
    }
}
