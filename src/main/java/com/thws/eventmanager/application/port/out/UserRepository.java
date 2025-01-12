package com.thws.eventmanager.application.port.out;

import com.thws.eventmanager.application.database.entities.UserEntity;
import com.thws.eventmanager.domain.models.User;

import java.util.List;

public interface UserRepository {
    void saveUser(UserEntity user);
    List<UserEntity> getAllUsers();
}
