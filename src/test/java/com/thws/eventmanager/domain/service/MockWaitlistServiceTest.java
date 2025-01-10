package com.thws.eventmanager.domain.service;

import static org.mockito.Mockito.*;

import com.thws.eventmanager.domain.models.User;
import com.thws.eventmanager.domain.models.Waitlist;
import com.thws.eventmanager.infrastructure.adapter.persistence.dbHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class MockWaitlistServiceTest {

    private dbHandler mockDbHandler;
    private WaitlistService waitlistService;
    private Waitlist mockWaitlist;
    private User mockUser;

    @BeforeEach
    void setUp() {
        mockDbHandler = mock(dbHandler.class);
        waitlistService = new WaitlistService(mockDbHandler);
        mockUser = mock(User.class);
    }

    @Test
    void testAddUserToWaitlist() {
        mockWaitlist = spy(new Waitlist("event123"));
        when(mockDbHandler.getWaitlistByEventId("event123")).thenReturn(mockWaitlist);

        waitlistService.addUserToWaitlist("event123", mockUser);

        assertEquals(mockUser, mockWaitlist.getFirstUser());
        verify(mockWaitlist).addUser(mockUser);
        verify(mockDbHandler).saveWaitlist(mockWaitlist);
    }

    @Test
    void testProcessNextUser() {
        mockWaitlist = mock(Waitlist.class);
        when(mockDbHandler.getWaitlistByEventId("event123")).thenReturn(mockWaitlist);
        when(mockWaitlist.getUsers()).thenReturn(List.of(mockUser));
        when(mockWaitlist.getFirstUser()).thenReturn(mockUser);

        Optional<User> processedUser = waitlistService.processNextUser("event123");

        assertTrue(processedUser.isPresent());
        assertEquals(mockUser, processedUser.get());
        verify(mockWaitlist).removeUser(mockUser);
        verify(mockDbHandler).saveWaitlist(mockWaitlist);
    }

    @Test
    void testGetUsersInWaitlist() {
        mockWaitlist = mock(Waitlist.class);
        when(mockDbHandler.getWaitlistByEventId("event123")).thenReturn(mockWaitlist);
        when(mockWaitlist.getUsers()).thenReturn(List.of(mockUser));

        List<User> users = waitlistService.getUsersInWaitlist("event123");

        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals(mockUser, users.getFirst());
    }

    @Test
    void testDeleteWaitlist() {
        waitlistService.deleteWaitlist("event123");

        verify(mockDbHandler).deleteWaitlistFromEvent("event123");
    }
}