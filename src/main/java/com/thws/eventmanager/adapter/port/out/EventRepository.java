package com.thws.eventmanager.adapter.port.out;

import com.thws.eventmanager.domain.models.Event;

import java.util.List;

public interface EventRepository {
    void saveEvent(Event event);
    List<Event> getAllEvents();
}
