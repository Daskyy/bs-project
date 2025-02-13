package com.thws.eventmanager.infrastructure.GraphQL.InputModels;

public class EventLocationInput {
    String id="-1";
    AddressInput address;
    String name;
    int capacity;


    public EventLocationInput(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AddressInput getAddress() {
        return address;
    }

    public void setAddress(AddressInput address) {
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
