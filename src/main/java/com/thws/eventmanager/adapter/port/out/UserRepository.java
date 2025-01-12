package com.thws.eventmanager.adapter.port.out;

import com.thws.eventmanager.infrastructure.adapter.persistence.entities.UserEntity;

import java.util.List;

public interface UserRepository {
    void saveUser(UserEntity user);
    List<UserEntity> getAllUsers();
}
