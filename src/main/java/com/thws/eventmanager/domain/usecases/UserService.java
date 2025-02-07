package com.thws.eventmanager.domain.usecases;

import com.thws.eventmanager.domain.models.User;
import com.thws.eventmanager.domain.port.in.UserServiceInterface;

public class UserService implements UserServiceInterface {
    @Override
    public User createUser(User user) {
        System.out.println(user.getName());
        System.out.println(user.getPassword());
        return user;
    }

    @Override
    public User validateUser(User user) {
        return null;
    }

    @Override
    public boolean deleteUser(User user) {
        return false;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }
}
