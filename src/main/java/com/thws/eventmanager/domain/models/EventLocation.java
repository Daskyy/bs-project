package com.thws.eventmanager.domain.models;

import jakarta.persistence.*;

public class EventLocation {
    int id;
    Address address;
    String name;
    int capacity;

    public EventLocation(Address adress, int capacity, String name) {
        this.address = adress;
        this.capacity = capacity;
        this.name = name;
    }
    public EventLocation(){}; // default constructor


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Address getAdress() {
        return address;
    }

    public void setAdress(Address adress) {
        this.address = adress;
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

    public Address getAddress() {
        return address;
    }
}
