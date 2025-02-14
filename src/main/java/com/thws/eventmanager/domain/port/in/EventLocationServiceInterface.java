package com.thws.eventmanager.domain.port.in;

import com.thws.eventmanager.domain.models.EventLocation;
import com.thws.eventmanager.infrastructure.components.persistence.entities.EventLocationEntity;

import java.util.List;
import java.util.Optional;

public interface EventLocationServiceInterface {
    EventLocationEntity saveLocation(EventLocation location);
    Optional<EventLocationEntity> deleteLocation(EventLocation location);
    Optional<EventLocationEntity> getLocationById(long id);
    List<EventLocationEntity> getAllEventLocations();
    List<EventLocationEntity> getAllEventLocations(List<String> criteria, List<Object> values);
}
