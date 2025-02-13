package com.thws.eventmanager.domain.port.in;
import com.thws.eventmanager.domain.models.User;
import com.thws.eventmanager.infrastructure.components.persistence.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserServiceInterface {
    UserEntity createUser(User user);

    Optional<UserEntity> deleteUser(User user);

    void validateUser(User user);

    List<UserEntity> getAllUsers(List<String> criteria, List<Object> values);

    List<UserEntity> getAllUsers();

    UserEntity getUserById(Long id);
//    boolean deleteUser(User user);
//    User updateUser(User user);
}
