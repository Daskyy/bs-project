package com.thws.eventmanager.domain.port.in;
import com.thws.eventmanager.domain.models.User;
import com.thws.eventmanager.infrastructure.components.persistence.entities.UserEntity;

import java.util.List;

public interface UserServiceInterface {
    UserEntity createUser(User user);
    void validateUser(User user);

    List<UserEntity> getAllUsers(List<String> criteria, List<Object> values);
//    boolean deleteUser(User user);
//    User updateUser(User user);
}
