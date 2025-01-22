package com.thws.eventmanager.domain.models;

import java.time.LocalDateTime;

public class EventSearchCriteria implements Model {
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final String description;
    private final EventLocation location;

    public EventSearchCriteria(LocalDateTime startDate, LocalDateTime endDate, String description, EventLocation location) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.location = location;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public String getDescription() {
        return description;
    }

    public EventLocation getLocation() {
        return location;
    }
}

