package com.thws.eventmanager.domain.usecases;

import com.thws.eventmanager.domain.port.in.EventSearchServiceInputPort;
import com.thws.eventmanager.domain.port.out.EventRepositoryOutputPort;
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

    // persistenceOutport.findAll() -> List<EventEntity>
    // eventMapper.toModel() -> List<Event>
    // filter -> List<Event>
    // return List<Event> ergebnisse

    @Override
    public List<Event> searchEvents(EventSearchCriteria criteria){
        //List<Event> list= persistenceOutport.findAll()
        return null;
    }
}
