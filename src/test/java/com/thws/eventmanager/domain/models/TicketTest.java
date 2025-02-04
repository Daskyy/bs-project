package com.thws.eventmanager.domain.models;

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
        assertThrows(IllegalArgumentException.class, ()->{ticket.setId(200L);});
    }
}