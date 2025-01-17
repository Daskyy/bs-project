package com.thws.eventmanager.domain.usecases;

import static org.mockito.Mockito.*;

import com.thws.eventmanager.infrastructure.components.persistence.entities.UserEntity;
import com.thws.eventmanager.infrastructure.components.persistence.entities.WaitlistEntity;
import com.thws.eventmanager.infrastructure.components.persistence.dbHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class MockWaitlistServiceTest {

    private dbHandler mockDbHandler;
    private WaitlistService waitlistService;
    private WaitlistEntity mockWaitlistEntity;
    private UserEntity mockUserEntity;

    @BeforeEach
    void setUp() {
        mockDbHandler = mock(dbHandler.class);
        waitlistService = new WaitlistService(mockDbHandler);
        mockUserEntity = mock(UserEntity.class);
    }

    @Test
    void testAddUserToWaitlist() {
        mockWaitlistEntity = spy(new WaitlistEntity("event123"));
        when(mockDbHandler.getWaitlistByEventId("event123")).thenReturn(mockWaitlistEntity);

        waitlistService.addUserToWaitlist("event123", mockUserEntity);

        assertEquals(mockUserEntity, mockWaitlistEntity.getFirstUser());
        verify(mockWaitlistEntity).addUser(mockUserEntity);
        verify(mockDbHandler).saveWaitlist(mockWaitlistEntity);
    }

    @Test
    void testProcessNextUser() {
        mockWaitlistEntity = mock(WaitlistEntity.class);
        when(mockDbHandler.getWaitlistByEventId("event123")).thenReturn(mockWaitlistEntity);
        when(mockWaitlistEntity.getUsers()).thenReturn(List.of(mockUserEntity));
        when(mockWaitlistEntity.getFirstUser()).thenReturn(mockUserEntity);

        Optional<UserEntity> processedUser = waitlistService.processNextUser("event123");

        assertTrue(processedUser.isPresent());
        assertEquals(mockUserEntity, processedUser.get());
        verify(mockWaitlistEntity).removeUser(mockUserEntity);
        verify(mockDbHandler).saveWaitlist(mockWaitlistEntity);
    }

    @Test
    void testGetUsersInWaitlist() {
        mockWaitlistEntity = mock(WaitlistEntity.class);
        when(mockDbHandler.getWaitlistByEventId("event123")).thenReturn(mockWaitlistEntity);
        when(mockWaitlistEntity.getUsers()).thenReturn(List.of(mockUserEntity));

        List<UserEntity> userEntities = waitlistService.getUsersInWaitlist("event123");

        assertNotNull(userEntities);
        assertEquals(1, userEntities.size());
        assertEquals(mockUserEntity, userEntities.getFirst());
    }

    @Test
    void testDeleteWaitlist() {
        waitlistService.deleteWaitlist("event123");

        verify(mockDbHandler).deleteWaitlistFromEvent("event123");
    }
}