package com.thws.eventmanager.domain.models;

import java.time.LocalDateTime;
import java.util.List;

public class EventSearchCriteria implements Model {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;
    private EventLocation location;
    private String name;
    private List<User> artists;



    public EventSearchCriteria(LocalDateTime startDate, LocalDateTime endDate, String description, EventLocation location, String name, List<User> artists) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.location = location;
        this.name=name;
        this.artists= artists;
    }
    public EventSearchCriteria(){

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

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(EventLocation location) {
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtists(List<User> artists) {
        this.artists = artists;
    }
}

