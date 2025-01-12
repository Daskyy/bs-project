package com.thws.eventmanager.infrastructure.adapter.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "eventlocations")
public class EventLocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @OneToOne
    AddressEntity addressEntity;
    @Column(name = "name", nullable = false)
    String name;
    @Column(name = "capacity", nullable = false)
    int capacity;


    public EventLocationEntity(AddressEntity adress, int capacity, String name) {
        this.addressEntity = adress;
        this.capacity = capacity;
        this.name = name;
    }
    public EventLocationEntity(){}; // default constructor


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AddressEntity getAdress() {
        return addressEntity;
    }

    public void setAdress(AddressEntity adress) {
        this.addressEntity = adress;
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

    public AddressEntity getAddress() {
        return addressEntity;
    }
}
