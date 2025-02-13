package com.thws.eventmanager.domain.services.models;

import com.thws.eventmanager.domain.exceptions.InvalidUserException;
import com.thws.eventmanager.domain.models.User;
import com.thws.eventmanager.domain.port.in.UserServiceInterface;
import com.thws.eventmanager.infrastructure.components.persistence.adapter.UserHandler;
import com.thws.eventmanager.infrastructure.components.persistence.entities.UserEntity;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.UserMapper;

import java.util.List;
import java.util.Optional;
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
    public Optional<UserEntity> deleteUser(User user) {
        try (UserHandler userHandler = new UserHandler()) {
            return userHandler.deleteById(user.getId());
        } catch (Exception e) {
            throw new InvalidUserException("Failed to delete user from database.");
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
    public List<UserEntity> getAllUsers(List<String> criteria, List<Object> values) {
        try (UserHandler userHandler = new UserHandler()) {
            if (criteria.size() != values.size()) {
                throw new InvalidUserException("Criteria and values lists must have the same size.");
            } else if (criteria.isEmpty()) {
                return userHandler.findAll();
            } else {
                return userHandler.searchByCriteria(criteria, values);
            }
        } catch (Exception e) {
            throw new InvalidUserException("Failed to get filtered users from database.");
        }
    }

    @Override
    public List<UserEntity> getAllUsers() {
        try (UserHandler userHandler = new UserHandler()) {
            return userHandler.findAll();
        } catch (Exception e) {
            throw new InvalidUserException("Failed to get users from database.");
        }
    }

    @Override
    public UserEntity getUserById(Long id) {
        try (UserHandler userHandler = new UserHandler()) {
            return userHandler.findById(id).orElseThrow(() -> new InvalidUserException("User not found"));
        } catch (Exception e) {
            throw new InvalidUserException("Failed to get user from database.");
        }
    }

//    @Override
//    public boolean deleteUser(User user) {
//        return false;
//    }
//
//    @Override
//    public User updateUser(User user) {
//        return null;
//    }
}
