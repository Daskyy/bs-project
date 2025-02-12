package com.thws.eventmanager.Unit.domain.usecases;

import com.thws.eventmanager.domain.exceptions.InvalidEventException;
import com.thws.eventmanager.domain.models.*;
import com.thws.eventmanager.domain.port.out.GenericPersistenceOutport;
import com.thws.eventmanager.domain.usecases.EventService;
import com.thws.eventmanager.infrastructure.components.persistence.adapter.EventHandler;
import com.thws.eventmanager.infrastructure.components.persistence.entities.EventEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EventServiceTest {

    private final EventHandler eventHandler = mock(EventHandler.class);
    private final EventService eventService = new EventService();

    private Event createValidEvent() {
        Event event = new Event();
        event.setName("Concert");
        event.setDescription("Live concert event.");
        event.setTicketCount(100);
        event.setTicketsSold(10);
        event.setMaxTicketsPerUser(5);
        User artistA = new User("Artist A", "", "", Permission.ARTIST);
        User artistB = new User("Artist B", "", "", Permission.ARTIST);
        event.setArtists(List.of(artistA, artistB));
        Address address = new Address();
        event.setLocation(new EventLocation(address, 3, ""));
        event.setBlockList(List.of());
        event.setStartDate(LocalDateTime.now().plusDays(1));
        event.setEndDate(LocalDateTime.now().plusDays(2));
        return event;
    }

    @Test
    void createEvent_saveWhenValid() {
        Event event = createValidEvent();
        EventEntity eventEntity = new EventEntity();

        when(eventHandler.save(any(EventEntity.class))).thenReturn(eventEntity);

        EventEntity result = eventService.createEvent(event);

        assertNotNull(result);
        verify(eventHandler, times(1)).save(any(EventEntity.class));
    }

    @Test
    void validateEvent_ExceptionWhenNameIsNull() {
        Event event = createValidEvent();
        event.setName(null);

        InvalidEventException exception = assertThrows(
                InvalidEventException.class,
                () -> eventService.validateEvent(event)
        );

        assertEquals("Event name cannot be null or empty.", exception.getMessage());
    }

    @Test
    void validateEvent_ExceptionWhenStartDateIsAfterEndDate() {
        Event event = createValidEvent();
        event.setStartDate(LocalDateTime.now().plusDays(3));
        event.setEndDate(LocalDateTime.now().plusDays(2));

        InvalidEventException exception = assertThrows(
                InvalidEventException.class,
                () -> eventService.validateEvent(event)
        );

        assertEquals("Start date cannot be after the end date.", exception.getMessage());
    }

    @Test
    void validateEvent_ExceptionWhenTicketsSoldExceedTotal() {
        Event event = createValidEvent();
        event.setTicketsSold(101); // Exceeds ticketCount 100

        InvalidEventException exception = assertThrows(
                InvalidEventException.class,
                () -> eventService.validateEvent(event)
        );

        assertEquals("Tickets sold cannot exceed the total ticket count.", exception.getMessage());
    }

    @Test
    void createEvent_ShouldNotSave() {
        Event event = createValidEvent();
        event.setTicketCount(0); // Invalid ticket count

        assertThrows(InvalidEventException.class, () -> eventService.createEvent(event));

        verify(eventHandler, never()).save(any(EventEntity.class));
    }
}