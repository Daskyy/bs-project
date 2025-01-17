package com.thws.eventmanager.domain.port.out;

import com.thws.eventmanager.infrastructure.components.persistence.entities.UserEntity;

import java.util.List;

public interface UserRepository {
    void saveUser(UserEntity user);
    List<UserEntity> getAllUsers();
}
