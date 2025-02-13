package com.thws.eventmanager.Unit.domain.models;

import com.thws.eventmanager.domain.exceptions.InvalidEventException;
import com.thws.eventmanager.domain.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {
    private Event event;

    @BeforeEach
    void setUp() {
        event = new Event();
        User user3 = new User();
        User artist = new User();
        artist.setName("artist");
        artist.setPermission(Permission.ARTIST);

        EventLocation location = new EventLocation();
        location.setName("Sample Venue");
        location.setAddress(new Address());

        event.setName("Sample Event");
        event.setDescription("This is a sample event");
        event.setTicketCount(1000);
        event.setTicketsSold(500);
        event.setMaxTicketsPerUser(5);
        event.setStartDate(LocalDateTime.of(2025, 12, 15, 10, 10));
        event.setEndDate(LocalDateTime.of(2025, 12, 15, 10, 15));
        event.setArtists(new ArrayList<>(List.of(artist)));
        event.setBlockList(new ArrayList<>(List.of(user3)));
        event.setLocation(location);
    }

    //event id tests
    @Test
    void setEventId() {
        event.setId(100L);
        assertEquals(100L, event.getId());
    }
    @Test
    void setEventIdAgain() {
        event.setId(100L);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {event.setId(200L);});
        assertEquals("ID is already set", exception.getMessage());
    }

    //adding artist tests
    @Test
    void addArtistWithPermission() {
        User artist = new User("a", "", "", Permission.ARTIST);
        event.setArtists(List.of(artist));
        assertTrue(event.getArtists().contains(artist));
    }
    @Test
    void addArtistAgain() {
        User artist = new User("a", "", "", Permission.ARTIST);
        event.setArtists(List.of(artist));
        InvalidEventException exception = assertThrows(InvalidEventException.class, () -> {event.addArtist(artist);});
        assertEquals("This artist is already part of the event.", exception.getMessage());
    }
    @Test
    void addArtistWithoutPermission() {
        User user = new User("a", "", "", Permission.CUSTOMER);
        InvalidEventException exception = assertThrows(InvalidEventException.class, () -> {event.addArtist(user);});
        assertEquals("Only users with ARTIST permission can be added as artists.", exception.getMessage());
    }
    @Test
    void addNullArtist() {
        User user = null;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {event.addArtist(user);});
        assertEquals("Artist cannot be null.", exception.getMessage());
    }

    //removing artist tests
    @Test
    void removeArtistTest() {
        User artist = new User("a", "", "", Permission.ARTIST);
        event.addArtist(artist);
        event.removeArtist(artist);
        assertFalse(event.getArtists().contains(artist));
    }
    @Test
    void removeNonExistentArtist() {
        User artist = new User("a", "", "", Permission.ARTIST);
        InvalidEventException exception = assertThrows(InvalidEventException.class, () -> {event.removeArtist(artist);});
        assertEquals("The artist is not part of this event.", exception.getMessage());
    }
    @Test
    void removeNullArtist() {
        User artist = null;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {event.removeArtist(artist);});
        assertEquals("Artist cannot be null.", exception.getMessage());
    }

    //blocking user tests
    @Test
    void blockUserAgain() {
        User user = new User("", "", "", Permission.CUSTOMER);
        event.blockUser(user);
        InvalidEventException exception = assertThrows(InvalidEventException.class, () -> {event.blockUser(user);});
        assertEquals("User is already blocked for this event.", exception.getMessage());
    }
    @Test
    void blockNullUser() {
        User user = null;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {event.blockUser(user);});
        assertEquals("User cannot be null.", exception.getMessage());
    }


    @Test
    void unblockNonBlockedUser() {
        User user = new User("", "", "", Permission.CUSTOMER);
        InvalidEventException exception = assertThrows(InvalidEventException.class, () -> {event.unblockUser(user);});
        assertEquals("User is not blocked for this event.", exception.getMessage());
    }
    @Test
    void unblockNullUser() {
        User user = null;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {event.unblockUser(user);});
        assertEquals("User cannot be null.", exception.getMessage());
    }


    @Test
    void isBlockedNullUser() {
        User user = null;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {event.blockUser(user);});
        assertEquals("User cannot be null.", exception.getMessage());
    }
}
