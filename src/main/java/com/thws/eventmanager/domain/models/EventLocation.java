package com.thws.eventmanager.domain.models;

import jakarta.persistence.*;

@Entity
@Table(name = "eventlocations")
public class EventLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @OneToOne
    Address address;
    @Column(name = "name", nullable = false)
    String name;
    @Column(name = "capacity", nullable = false)
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
