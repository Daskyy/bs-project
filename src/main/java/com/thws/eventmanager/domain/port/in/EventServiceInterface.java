package com.thws.eventmanager.domain.port.in;
import com.thws.eventmanager.domain.exceptions.InvalidEventException;
import com.thws.eventmanager.domain.models.Event;
import com.thws.eventmanager.domain.models.EventLocation;
import com.thws.eventmanager.domain.models.User;

import java.time.LocalDateTime;
import java.util.List;

public interface EventServiceInterface {
    Event createEvent(Event event);
    void validateEvent(Event event);
}
