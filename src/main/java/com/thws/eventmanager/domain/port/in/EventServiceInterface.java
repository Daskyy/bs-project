package com.thws.eventmanager.domain.port.in;
import com.thws.eventmanager.domain.exceptions.InvalidEventException;
import com.thws.eventmanager.domain.models.Event;
import com.thws.eventmanager.domain.models.EventLocation;
import com.thws.eventmanager.domain.models.User;
import com.thws.eventmanager.infrastructure.components.persistence.entities.EventEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface EventServiceInterface {
    EventEntity createEvent(Event event);

    Optional<EventEntity> deleteEvent(Event event);

    Optional<EventEntity> getEventById(long id);

    List<EventEntity> getAllEvents(List<String> criteria, List<Object> values);

    List<EventEntity> getAllEvents();

    void validateEvent(Event event);
}
