package com.thws.eventmanager.domain.models;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
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