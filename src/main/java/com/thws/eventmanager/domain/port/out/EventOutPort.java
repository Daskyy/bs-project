package com.thws.eventmanager.domain.port.out;

import com.thws.eventmanager.domain.models.Event;

import java.util.List;

public interface EventOutPort {
    void saveEvent(Event event);
    List<Event> getAllEvents();
}
