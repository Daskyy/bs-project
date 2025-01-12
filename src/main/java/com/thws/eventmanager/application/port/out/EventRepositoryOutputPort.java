package com.thws.eventmanager.application.port.out;

import com.thws.eventmanager.domain.models.Event;
import com.thws.eventmanager.domain.models.EventSearchCriteria;

import java.util.List;

public interface EventRepositoryOutputPort {
    List<Event> findAllEvents();
    List<Event> findEventsByCriteria(EventSearchCriteria criteria);
}
