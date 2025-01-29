package com.thws.eventmanager.infrastructure.GraphQL.Models;

public class EventLocationGQL implements GQLModel{
    private String id;
    private AddressGQL address;
    private String name;
    private int capacity;

    public EventLocationGQL() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AddressGQL getAddress() {
        return address;
    }

    public void setAddress(AddressGQL address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
