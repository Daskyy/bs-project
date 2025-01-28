package com.thws.eventmanager.domain.port.out;

import com.thws.eventmanager.domain.models.Event;
import com.thws.eventmanager.domain.models.EventSearchCriteria;

import java.util.List;

public interface EventRepositoryOutputPort {
    List<Event> findEventsByCriteria(EventSearchCriteria criteria);
}
