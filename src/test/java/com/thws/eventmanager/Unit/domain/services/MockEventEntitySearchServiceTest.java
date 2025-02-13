/*
package com.thws.eventmanager.domain.usecases;

import com.thws.eventmanager.domain.port.out.EventRepositoryOutputPort;
import com.thws.eventmanager.domain.models.Event;
import com.thws.eventmanager.domain.port.out.GenericPersistenceOutport;
import com.thws.eventmanager.infrastructure.components.persistence.entities.EventEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class MockEventEntitySearchServiceTest {
    private GenericPersistenceOutport<EventEntity, Long> mockpersistenceOutport;
    private EventSearchService eventSearchService;

    @BeforeEach
    void setUp(){
        mockpersistenceOutport = mock(GenericPersistenceOutport.class);
        eventSearchService = new EventSearchService(mockpersistenceOutport);
    }

    // TODO: FIX CONSTRUCTOR IN EVENTLOCATION DOMAIN LEVEL
*/
/*    @Test
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
    }*/
/*
import com.thws.eventmanager.domain.models.Event;
import com.thws.eventmanager.infrastructure.components.persistence.entities.EventEntity;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Test
    void testSearchEventsWithoutCriteria() {
        Event event = mock(Event.class);
        when(mockpersistenceOutport.findAll()).thenReturn(List.of(new EventEntity()));

        List<Event> events = eventSearchService.searchEvents(null);

        assertNotNull(events);
        assertEquals(1, events.size());
    }
}
*/
