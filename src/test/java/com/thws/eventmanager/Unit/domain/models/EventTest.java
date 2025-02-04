package com.thws.eventmanager.Unit.domain.models;

import com.thws.eventmanager.domain.exceptions.InvalidEventException;
import com.thws.eventmanager.domain.models.Event;
import com.thws.eventmanager.domain.models.Permission;
import com.thws.eventmanager.domain.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {
    private Event event;

    @BeforeEach
    void setUp() {
        event = new Event();
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
        assertThrows(IllegalArgumentException.class, () -> {event.setId(200L);});
    }

    //adding artist tests
    @Test
    void addArtistWithPermission() {
        User artist = new User("a", "", "", Permission.ARTIST);
        event.addArtist(artist);
        assertTrue(event.getArtists().contains(artist));
    }
    @Test
    void addArtistAgain() {
        User artist = new User("a", "", "", Permission.ARTIST);
        event.addArtist(artist);
        assertThrows(InvalidEventException.class, () -> {event.addArtist(artist);});
    }
    @Test
    void addArtistWithoutPermission() {
        User user = new User("a", "", "", Permission.CUSTOMER);
        assertThrows(InvalidEventException.class, () -> {event.addArtist(user);});
    }
    @Test
    void addNullArtist() {
        User user = null;
        assertThrows(IllegalArgumentException.class, () -> {event.addArtist(user);});
    }

    //removing artist tests
    @Test
    void removeArtist() {
        User artist = new User("a", "", "", Permission.ARTIST);
        event.addArtist(artist);
        event.removeArtist(artist);
        assertFalse(event.getArtists().contains(artist));
    }
    @Test
    void removeNonExistentArtist() {
        User artist = new User("a", "", "", Permission.ARTIST);
        assertThrows(InvalidEventException.class, () -> {event.removeArtist(artist);});
    }
    @Test
    void removeNullArtist() {
        User artist = null;
        assertThrows(IllegalArgumentException.class, () -> {event.removeArtist(artist);});
    }

    //blocking user tests
    @Test
    void blockUser() {
        User user = new User("", "", "", Permission.CUSTOMER);
        event.blockUser(user);
        assertTrue(event.isBlocked(user));
    }
    @Test
    void blockUserAgain() {
        User user = new User("", "", "", Permission.CUSTOMER);
        event.blockUser(user);
        assertThrows(InvalidEventException.class, () -> {event.blockUser(user);});
    }
    @Test
    void blockNullUser() {
        User user = null;
        assertThrows(IllegalArgumentException.class, () -> {event.blockUser(user);});
    }

    //unblocking user tests
    @Test
    void unblockUser() {
        User user = new User("", "", "", Permission.CUSTOMER);
        event.blockUser(user);
        event.unblockUser(user);
        assertFalse(event.isBlocked(user));
    }
    @Test
    void unblockNonBlockedUser() {
        User user = new User("", "", "", Permission.CUSTOMER);
        assertThrows(InvalidEventException.class, () -> {event.unblockUser(user);});
    }
    @Test
    void unblockNullUser() {
        User user = null;
        assertThrows(IllegalArgumentException.class, () -> {event.unblockUser(user);});
    }

    //checking if user is blocked tests
    @Test
    void isBlocked() {
        User user = new User("", "", "", Permission.CUSTOMER);
        event.blockUser(user);
        assertTrue(event.isBlocked(user));
    }
    @Test
    void isBlockedNullUser() {
        User user = null;
        assertThrows(IllegalArgumentException.class, () -> {event.blockUser(user);});
    }

}