package com.thws.eventmanager.infrastructure.GraphQL.InputModels;

public class EventInput {
    private String name;
    private String description;
    private EventLocationInput location;

    public EventInput() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
}
