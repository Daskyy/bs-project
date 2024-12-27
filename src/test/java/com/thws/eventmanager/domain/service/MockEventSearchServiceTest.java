package com.thws.eventmanager.domain.service;

import com.thws.eventmanager.application.port.out.EventRepositoryOutputPort;
import com.thws.eventmanager.domain.models.Address;
import com.thws.eventmanager.domain.models.Event;
import com.thws.eventmanager.domain.models.EventLocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class MockEventSearchServiceTest {
    private EventRepositoryOutputPort mockEventRepository;
    private EventSearchService eventSearchService;

    @BeforeEach
    void setUp(){
        mockEventRepository = mock(EventRepositoryOutputPort.class);
        eventSearchService = new EventSearchService(mockEventRepository);
    }

    @Test
    void testSearchEventsWithCriteria() {
        Address address = new Address("Springfield", "USA", 123, "Main St", 12345);
        EventLocation location = new EventLocation(address, 100, "Concert Hall", 1234567890);

        EventSearchCriteria criteria = new EventSearchCriteria(
                LocalDateTime.of(2024, 12, 1, 0, 0),
                LocalDateTime.of(2024, 12, 31, 23, 59),
                "Music",
                location
        );

        Event event = mock(Event.class);
        when(mockEventRepository.findEventsByCriteria(criteria)).thenReturn(List.of(event));

        List<Event> events = eventSearchService.searchEvents(criteria);

        assertNotNull(events);
        assertEquals(1, events.size());
        assertEquals(event, events.get(0));
    }
    @Test
    void testSearchEventsWithoutCriteria() {
        Event event = mock(Event.class);
        when(mockEventRepository.findAllEvents()).thenReturn(List.of(event));

        List<Event> events = eventSearchService.searchEvents(null);

        assertNotNull(events);
        assertEquals(1, events.size());
        assertEquals(event, events.get(0));

        verify(mockEventRepository).findAllEvents();
    }
}