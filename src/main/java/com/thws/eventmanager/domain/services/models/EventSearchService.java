package com.thws.eventmanager.domain.services.models;

import com.thws.eventmanager.domain.port.in.EventSearchServiceInputPort;
import com.thws.eventmanager.domain.models.Event;
import com.thws.eventmanager.domain.models.EventSearchCriteria;
import com.thws.eventmanager.domain.port.out.GenericPersistenceOutport;
import com.thws.eventmanager.infrastructure.components.persistence.entities.EventEntity;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.EventMapper;

import java.util.List;

public class EventSearchService implements EventSearchServiceInputPort {
    private final GenericPersistenceOutport<EventEntity, Long> persistenceOutport;
    private final EventMapper eventMapper;

    public EventSearchService(GenericPersistenceOutport<EventEntity, Long> persistenceOutport) {
        this.persistenceOutport = persistenceOutport;
        this.eventMapper = new EventMapper();
    }

    @Override
    public List<Event> searchEvents(EventSearchCriteria criteria) {
        return persistenceOutport.findAll().stream()
                .map(eventMapper::toModel)
                .filter(event -> criteria.getName() == null || event.getName().equals(criteria.getName()))
                .filter(event -> criteria.getArtists() == null || event.getArtists().containsAll(criteria.getArtists()))
                .filter(event -> criteria.getDescription() == null || event.getDescription().equals(criteria.getDescription()))
                .filter(event -> criteria.getLocation() == null || event.getLocation().equals(criteria.getLocation()))
                .filter(event -> criteria.getStartDate() == null || event.getStartDate().equals(criteria.getStartDate()))
                .filter(event -> criteria.getEndDate() == null || event.getEndDate().equals(criteria.getEndDate()))
                .toList();
    }
}