package com.thws.eventmanager.Unit.domain.usecases;

import com.thws.eventmanager.domain.models.Event;
import com.thws.eventmanager.domain.models.EventLocation;
import com.thws.eventmanager.domain.models.EventSearchCriteria;
import com.thws.eventmanager.domain.port.in.EventSearchServiceInputPort;
import com.thws.eventmanager.domain.port.out.GenericPersistenceOutport;
import com.thws.eventmanager.domain.services.models.EventSearchService;
import com.thws.eventmanager.infrastructure.components.persistence.entities.AddressEntity;
import com.thws.eventmanager.infrastructure.components.persistence.entities.EventEntity;
import com.thws.eventmanager.infrastructure.components.persistence.entities.EventLocationEntity;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.EventMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EventSearchServiceTest {
    private GenericPersistenceOutport<EventEntity, Long> persistenceOutport;
    private EventMapper eventMapper;
    private EventSearchServiceInputPort eventSearchService;

    @BeforeEach
    void setUp() {
        persistenceOutport = mock(GenericPersistenceOutport.class);
        eventMapper = mock(EventMapper.class);
        eventSearchService = new EventSearchService(persistenceOutport);
    }

    private EventEntity createEventEntity() {
        //this method returns a valid Entity for the proper functioning of mappers and initialization of classes
        EventEntity eventEntity = new EventEntity();
        eventEntity.setName("Concert");

        EventLocationEntity eventLocationEntity = new EventLocationEntity();
        eventLocationEntity.setAddress(new AddressEntity());
        eventLocationEntity.setName("test location");
        eventLocationEntity.setCapacity(3);
        eventLocationEntity.setId(100L);

        eventEntity.setLocation(eventLocationEntity);
        eventEntity.setStartDate(LocalDateTime.of(2024, 5, 10, 18, 0));

        return eventEntity;
    }

    private Event createEvent() {
        //this method returns a valid event models for the proper functioning of mappers and initialization of classes
        EventLocation location = new EventLocation();
        location.setName("test location");
        location.setCapacity(3);
        location.setId(100L);

        Event event = new Event();
        event.setName("Concert");
        event.setLocation(location);
        event.setStartDate(LocalDateTime.of(2024, 5, 10, 18, 0));

        return event;
    }

    @Test
    void returnMatchingName() {
        EventEntity eventEntity = createEventEntity();
        Event event = createEvent();

        when(persistenceOutport.findAll()).thenReturn(List.of(eventEntity));
        when(eventMapper.toModel(eventEntity)).thenReturn(event);

        EventSearchCriteria criteria = new EventSearchCriteria();
        criteria.setName("Concert");

        List<Event> result = eventSearchService.searchEvents(criteria);

        assertEquals(1, result.size());
        assertEquals("Concert", result.getFirst().getName());
    }

    @Test
    void filterByLocationAndDate() {
        EventEntity eventEntity = createEventEntity();
        Event event = createEvent();

        when(persistenceOutport.findAll()).thenReturn(List.of(eventEntity));
        when(eventMapper.toModel(eventEntity)).thenReturn(event);

        EventSearchCriteria criteria = new EventSearchCriteria();
        criteria.setLocation(event.getLocation());
        criteria.setStartDate(LocalDateTime.of(2024, 5, 10, 18, 0));

        List<Event> result = eventSearchService.searchEvents(criteria);

        assertEquals(1, result.size());
        assertEquals(LocalDateTime.of(2024, 5, 10, 18, 0), result.getFirst().getStartDate());
    }
    @Test
    void emptyListForNotMatchingName() {
        EventEntity eventEntity = createEventEntity();

        Event event = createEvent();

        when(persistenceOutport.findAll()).thenReturn(List.of(eventEntity));
        when(eventMapper.toModel(eventEntity)).thenReturn(event);

        EventSearchCriteria criteria = new EventSearchCriteria();
        criteria.setName("Conference");

        List<Event> result = eventSearchService.searchEvents(criteria);

        assertEquals(0, result.size());
    }
}