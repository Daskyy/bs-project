package com.thws.eventmanager.domain.models.UnitTest;

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
        assertThrows(IllegalArgumentException.class, ()->{user.setId(200L);});
    }
}