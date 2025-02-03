package com.thws.eventmanager.domain.models;

import java.time.LocalDateTime;
import java.util.List;

public class EventSearchCriteria implements Model {
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final String description;
    private final EventLocation location;
    private final String name;
    private final List<User> artists;



    public EventSearchCriteria(LocalDateTime startDate, LocalDateTime endDate, String description, EventLocation location, String name, List<User> artists) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.location = location;
        this.name=name;
        this.artists= artists;
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
    public String getName(){
        return this.name;
    }
    public List<User> getArtists(){
        return this.artists;
    }
}

