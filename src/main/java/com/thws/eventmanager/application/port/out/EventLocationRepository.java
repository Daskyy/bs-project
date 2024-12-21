package com.thws.eventmanager.application.port.out;

import com.thws.eventmanager.domain.models.EventLocation;

import java.util.List;

public interface EventLocationRepository {
    void saveEventLocation(EventLocation eventLocation);
    List<EventLocation> getAllEventLocations();
}
