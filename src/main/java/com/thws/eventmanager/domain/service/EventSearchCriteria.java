package com.thws.eventmanager.domain.service;

import com.thws.eventmanager.domain.models.EventLocation;

import java.time.LocalDateTime;

public class EventSearchCriteria {
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final String category;
    private final EventLocation location;

    public EventSearchCriteria(LocalDateTime startDate, LocalDateTime endDate, String category, EventLocation location) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.category = category;
        this.location = location;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public String getCategory() {
        return category;
    }

    public EventLocation getLocation() {
        return location;
    }
}

