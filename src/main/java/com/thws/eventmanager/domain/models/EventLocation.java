package com.thws.eventmanager.domain.models;

import jakarta.persistence.*;

import java.util.Objects;

public class EventLocation implements Model {
    Address address;
    String name;
    int capacity;
    long id = -1;

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

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        if (this.id != -1) {
            throw new IllegalArgumentException("ID is already set");
        } else {
            this.id = id;
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventLocation other = (EventLocation) o;

        // If both IDs are assigned, compare them directly
        if (this.id != -1 && other.id != -1) {
            return this.id == other.id;
        }

        // If any ID is not assigned, compare the other attributes
        return capacity == other.capacity &&
               Objects.equals(name, other.name) &&
               Objects.equals(address, other.address);
    }

    @Override
    public int hashCode() {
        // Include id in hashCode if it is not -1
        return Objects.hash(id == -1 ? 0 : id, name, capacity, address);
    }
}
