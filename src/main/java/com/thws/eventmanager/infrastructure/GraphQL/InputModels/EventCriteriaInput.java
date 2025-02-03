package com.thws.eventmanager.infrastructure.GraphQL.InputModels;

import java.util.List;

public class EventCriteriaInput {
    private String description;
    private EventLocationInput location;
    private String name;
    private List<UserInput> artists;

    public EventCriteriaInput() {}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EventLocationInput getLocation() {
        return location;
    }

    public void setLocation(EventLocationInput location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserInput> getArtists() {
        return artists;
    }

    public void setArtists(List<UserInput> artists) {
        this.artists = artists;
    }
}
