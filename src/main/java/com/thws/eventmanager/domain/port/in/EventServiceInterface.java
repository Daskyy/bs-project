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
    List<EventEntity> getAllEvents(List<String> criteria, List<Object> values, Integer page, Integer pageSize);
    List<EventEntity> getAllEvents();
    boolean isBlocked(Event event, User user);
    boolean isArtist(Event event, User user);
    EventEntity blockUser(Event event, User user);
    List<EventEntity> getTrendingsEvents(int page, int pageSize);
    boolean refundEvent(Event event);
    void validateEvent(Event event);
}
