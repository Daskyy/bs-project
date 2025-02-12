package com.thws.eventmanager.domain.port.in;
import com.thws.eventmanager.domain.models.User;
import com.thws.eventmanager.infrastructure.components.persistence.entities.UserEntity;

public interface UserServiceInterface {
    UserEntity createUser(User user);
    void validateUser(User user);
//    boolean deleteUser(User user);
//    User updateUser(User user);
}
