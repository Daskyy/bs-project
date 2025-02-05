package com.thws.eventmanager.Unit.domain.models;

import com.thws.eventmanager.domain.models.EventLocation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventLocationTest {
    @Test
    void setId(){
        EventLocation eventLocation = new EventLocation();
        eventLocation.setId(100L);
        assertEquals(100L, eventLocation.getId());
    }
    @Test
    void setIdAgain(){
        EventLocation eventLocation = new EventLocation();
        eventLocation.setId(100L);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()->{eventLocation.setId(200L);});
        assertEquals("ID is already set", exception.getMessage());
    }
}