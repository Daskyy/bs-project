package com.thws.eventmanager.adapter.port.in;

import com.thws.eventmanager.domain.models.Event;
import com.thws.eventmanager.domain.service.EventSearchCriteria;

import java.util.List;

public interface EventSearchServiceInputPort {
    List<Event> searchEvents(EventSearchCriteria criteria);
}
