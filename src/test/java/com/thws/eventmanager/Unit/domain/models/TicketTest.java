package com.thws.eventmanager.Unit.domain.models;

import com.thws.eventmanager.domain.models.Ticket;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicketTest {
    @Test
    void setId(){
        Ticket ticket = new Ticket();
        ticket.setId(100L);
        assertEquals(100L, ticket.getId());
    }
    @Test
    void setIdAgain(){
        Ticket ticket = new Ticket();
        ticket.setId(100L);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()->{ticket.setId(200L);});
        assertEquals("ID is already set", exception.getMessage());
    }
}