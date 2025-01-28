package com.thws.eventmanager.infrastructure.components.persistence.adapter;

import com.thws.eventmanager.infrastructure.components.persistence.entities.UserEntity;
import jakarta.persistence.EntityManager;

// -> Handler sind extended Adapter

public class UserHandler extends GenericPersistenceAdapter<UserEntity, Long> {
    public UserHandler(EntityManager entityManager) {
        super(entityManager, UserEntity.class);
    }
}
