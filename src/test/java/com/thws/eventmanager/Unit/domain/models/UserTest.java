package com.thws.eventmanager.Unit.domain.models;

import com.thws.eventmanager.domain.models.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void setId(){
        User user = new User();
        user.setId(100L);
        assertEquals(100L, user.getId());
    }
    @Test
    void setIdAgain(){
        User user = new User();
        user.setId(100L);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()->{user.setId(200L);});
        assertEquals("ID is already set", exception.getMessage());
    }
}