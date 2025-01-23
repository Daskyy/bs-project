package com.thws.eventmanager.domain.models;

import jakarta.persistence.*;

public class EventLocation implements Model {
    Address address;
    String name;
    int capacity;

    public EventLocation(Address address, int capacity, String name) {
        this.address = address;
        this.capacity = capacity;
        this.name = name;
    }
    public EventLocation(){}; // default constructor

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
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
