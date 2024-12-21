package com.thws.eventmanager.application.port.out;

import com.thws.eventmanager.domain.models.Event;
import com.thws.eventmanager.domain.models.User;

import java.util.List;

public interface EventRepository {
    void saveEvent(Event event);
    List<Event> getAllEvents();
}
