package com.thws.eventmanager.application.database.service;

import com.thws.eventmanager.application.port.in.EventSearchServiceInputPort;
import com.thws.eventmanager.application.port.out.EventRepositoryOutputPort;
import com.thws.eventmanager.domain.models.Event;
import com.thws.eventmanager.domain.models.EventSearchCriteria;

import java.util.List;

public class EventSearchService implements EventSearchServiceInputPort {
    private final EventRepositoryOutputPort eventRepository;

    public EventSearchService(EventRepositoryOutputPort eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> searchEvents(EventSearchCriteria criteria){
        if (criteria == null){
            return eventRepository.findAllEvents();
        }

        return eventRepository.findEventsByCriteria(criteria);
    }
}
