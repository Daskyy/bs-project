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
}