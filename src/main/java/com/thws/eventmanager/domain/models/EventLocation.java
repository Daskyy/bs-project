package com.thws.eventmanager.domain.models;
import com.thws.eventmanager.domain.models.*;
public class EventLocation {
    int id;
    Address adress;
    String name;
    int capacity;
    public EventLocation(Address adress, int capacity, String name, int id) {
        this.adress = adress;
        this.capacity = capacity;
        this.name = name;
        this.id = id;
    }
    public EventLocation(){}; // default constructor


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Address getAdress() {
        return adress;
    }

    public void setAdress(Address adress) {
        this.adress = adress;
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
