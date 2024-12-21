package com.thws.eventmanager.application.port.out;

import com.thws.eventmanager.domain.models.User;

import java.util.List;

public interface UserRepository {
    void saveUser(User user);
    List<User> getAllUsers();
}
