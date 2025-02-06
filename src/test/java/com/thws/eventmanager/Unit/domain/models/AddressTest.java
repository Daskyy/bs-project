package com.thws.eventmanager.Unit.domain.models;

import com.thws.eventmanager.domain.models.Address;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {
    @Test
    void setIdTest() {
        Address address = new Address();
        address.setId(100);
        assertEquals(100, address.getId());
    }

    @Test
    void setAgainThrowsExceptionTest() {
        Address address = new Address();
        address.setId(100);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            address.setId(200);});

        assertEquals("ID is already set", exception.getMessage());
    }

    @Test
    void equalsTest() {
        Address address1 = new Address("Berlin", "Germany", 42, "Main Street", 10115);
        address1.setId(1);
        Address address2 = new Address("Berlin", "Germany", 42, "Main Street", 10115);
        address2.setId(1);

        assertEquals(address1, address2);
    }

    @Test
    void notEqualsTest() {
        Address address1 = new Address("Berlin", "Germany", 42, "Main Street", 10115);
        address1.setId(1);
        Address address2 = new Address("Berlin", "Germany", 42, "Main Street", 10115);
        address2.setId(2); //different id

        assertNotEquals(address1, address2);
    }

    @Test
    void equalHashCodeTest() {
        Address address1 = new Address("Berlin", "Germany", 42, "Main Street", 10115);
        address1.setId(1);
        Address address2 = new Address("Berlin", "Germany", 42, "Main Street", 10115);
        address2.setId(1);

        assertEquals(address1.hashCode(), address2.hashCode());
    }

    @Test
    void notEqualsHashCodeTest() {
        Address address1 = new Address("Berlin", "Germany", 42, "Main Street", 10115);
        address1.setId(1);
        Address address2 = new Address("Paris", "France", 23, "Rue de Rivoli", 75001);
        address2.setId(2); //different id

        assertNotEquals(address1.hashCode(), address2.hashCode());
    }
}