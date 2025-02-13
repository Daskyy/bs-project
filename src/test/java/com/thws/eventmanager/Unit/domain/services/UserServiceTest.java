package com.thws.eventmanager.Unit.domain.services;

import com.thws.eventmanager.domain.exceptions.InvalidUserException;
import com.thws.eventmanager.domain.models.User;
import com.thws.eventmanager.domain.services.models.UserService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private final UserService userService = new UserService();

    @Test
    void validUserTest() {
        User user = new User();
        user.setName("Valid Name");
        user.setEmail("valid.email@example.com");
        user.setPassword("validpassword123");

        assertDoesNotThrow(() -> userService.validateUser(user));
    }

    @Test
    void invalidUserNameTest() {
        User user = new User();
        user.setEmail("valid.email@example.com");
        user.setPassword("validpassword123");
        //null name
        user.setName(null);
        InvalidUserException exception1 = assertThrows(InvalidUserException.class, () -> userService.validateUser(user));
        assertEquals("User name cannot be null or empty.", exception1.getMessage());
        //empty name
        user.setName("");
        InvalidUserException exception2 = assertThrows(InvalidUserException.class, () -> userService.validateUser(user));
        assertEquals("User name cannot be null or empty.", exception2.getMessage());
    }

    @Test
    void invalidEmailTest() {
        User user = new User();
        user.setName("Valid Name");
        user.setPassword("validpassword123");

        // Invalid email
        user.setEmail("invalid-email.com");
        InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userService.validateUser(user));
        assertEquals("Email is not a valid email address.", exception.getMessage());
    }

    @Test
    void invalidPasswordTest() {
        User user = new User();
        user.setName("Valid Name");
        user.setEmail("valid.email@example.com");

        // Password is null
        user.setPassword(null);
        InvalidUserException exception1 = assertThrows(InvalidUserException.class, () -> userService.validateUser(user));
        assertEquals("Password cannot be null or empty.", exception1.getMessage());

        // Password is too short
        user.setPassword("short");
        InvalidUserException exception2 = assertThrows(InvalidUserException.class, () -> userService.validateUser(user));
        assertEquals("Password must be at least 8 characters.", exception2.getMessage());
    }

}