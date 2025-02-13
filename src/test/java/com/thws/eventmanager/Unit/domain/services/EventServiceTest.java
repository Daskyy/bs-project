package com.thws.eventmanager.Unit.domain.services;

import com.thws.eventmanager.domain.exceptions.InvalidEventException;
import com.thws.eventmanager.domain.models.*;
import com.thws.eventmanager.domain.services.models.EventService;
import com.thws.eventmanager.infrastructure.components.persistence.adapter.EventHandler;
import com.thws.eventmanager.infrastructure.components.persistence.entities.EventEntity;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.EventMapper;
import org.junit.jupiter.api.Test;

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
        event.setTicketCount(100L);
        event.setTicketsSold(10L);
        event.setMaxTicketsPerUser(5);
        User artistA = new User("Artist A", "example123@gamil.com", "38475", Permission.ARTIST);
        User artistB = new User("Artist B", "example234@gmail.com", "93846", Permission.ARTIST);
        event.setArtists(List.of(artistA, artistB));
        Address address = new Address();
        address.setStreet("Street");
        address.setCity("City");
        address.setId(1543L);
        address.setCountry("Country");
        address.setNo(19);
        address.setZipCode(12345);
        event.setLocation(new EventLocation(address, 3, ""));
        event.setBlockList(List.of());
        event.setStartDate(LocalDateTime.now().plusDays(1));
        event.setEndDate(LocalDateTime.now().plusDays(2));
        event.setTicketPrice(100L);
        return event;
    }

    @Test
    void createValidEventTest() {
        Event event = createValidEvent();
        assertDoesNotThrow(() -> eventService.validateEvent(event));
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