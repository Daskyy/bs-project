package com.thws.eventmanager.application.port.in;

import com.thws.eventmanager.domain.models.Event;
import com.thws.eventmanager.domain.models.EventSearchCriteria;

import java.util.List;

public interface EventSearchServiceInputPort {
    List<Event> searchEvents(EventSearchCriteria criteria);
}
