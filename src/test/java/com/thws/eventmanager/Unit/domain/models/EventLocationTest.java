package com.thws.eventmanager.Unit.domain.models;

import com.thws.eventmanager.domain.models.Address;
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

    @Test
    void equalsTest() {
        Address address1 = new Address("Berlin", "Germany", 42, "Main Street", 10115);
        EventLocation location1 = new EventLocation(address1, 100, "Conference Hall");
        location1.setId(1L);

        Address address2 = new Address("Berlin", "Germany", 42, "Main Street", 10115);
        EventLocation location2 = new EventLocation(address2, 100, "Conference Hall");
        location2.setId(1L);

        assertEquals(location1, location2);
    }

    @Test
    void notEqualsTest() {
        Address address1 = new Address("Berlin", "Germany", 42, "Main Street", 10115);
        EventLocation location1 = new EventLocation(address1, 100, "Conference Hall");
        location1.setId(1L);

        Address address2 = new Address("Berlin", "Germany", 42, "Main Street", 10115);
        EventLocation location2 = new EventLocation(address2, 100, "Music Hall");
        location2.setId(2L);

        assertNotEquals(location1, location2);
    }

    @Test
    void equalsHashCodeTest() {
        Address address1 = new Address("Berlin", "Germany", 42, "Main Street", 10115);
        EventLocation location1 = new EventLocation(address1, 100, "Conference Hall");
        location1.setId(1L);

        Address address2 = new Address("Berlin", "Germany", 42, "Main Street", 10115);
        EventLocation location2 = new EventLocation(address2, 100, "Conference Hall");
        location2.setId(1L);

        assertEquals(location1.hashCode(), location2.hashCode());
    }

    @Test
    void notEqualsHashCodeTest() {
        Address address1 = new Address("Berlin", "Germany", 42, "Main Street", 10115);
        EventLocation location1 = new EventLocation(address1, 100, "Conference Hall");
        location1.setId(1L);

        Address address2 = new Address("Paris", "France", 23, "Rue de Rivoli", 75001);
        EventLocation location2 = new EventLocation(address2, 150, "Exhibition Center");
        location2.setId(2L); // different id

        assertNotEquals(location1.hashCode(), location2.hashCode());
    }
}