package com.thws.eventmanager.domain.usecases;

import com.thws.eventmanager.domain.exceptions.InvalidUserException;
import com.thws.eventmanager.domain.models.User;
import com.thws.eventmanager.domain.port.in.UserServiceInterface;
import com.thws.eventmanager.infrastructure.components.persistence.adapter.UserHandler;
import com.thws.eventmanager.infrastructure.components.persistence.entities.UserEntity;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.UserMapper;

import java.util.regex.Pattern;

public class UserService implements UserServiceInterface {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private final UserMapper userMapper = new UserMapper();

    @Override
    public UserEntity createUser(User user) {
        validateUser(user);
        try (UserHandler userHandler = new UserHandler()) {
            return userHandler.save(userMapper.toEntity(user));
        } catch (Exception e) {
            throw new InvalidUserException("Failed to create user");
        }
    }

    @Override
    public void validateUser(User user) {
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            throw new InvalidUserException("User name cannot be null or empty.");
        }

        validateEmail(user.getEmail());

        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new InvalidUserException("Password cannot be null or empty.");
        } else {
            if (user.getPassword().length() < 8) {
                throw new InvalidUserException("Password must be at least 8 characters.");
            }
        }
    }

    private void validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new InvalidUserException("Email cannot be null or empty.");
        } else {if (!EMAIL_PATTERN.matcher(email).matches()) {
                throw new InvalidUserException("Email is not a valid email address.");
            }
        }
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
