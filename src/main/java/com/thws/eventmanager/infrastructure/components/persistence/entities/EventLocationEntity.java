package com.thws.eventmanager.infrastructure.components.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "eventlocations")
public class EventLocationEntity implements PersistenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @OneToOne
    AddressEntity addressEntity;
    @Column(name = "name", nullable = false)
    String name;
    @Column(name = "capacity", nullable = false)
    int capacity;


    public EventLocationEntity(AddressEntity address, int capacity, String name) {
        this.addressEntity = address;
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

    public AddressEntity getAddress() {
        return addressEntity;
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

    public void setAddress(AddressEntity entity) {
        this.addressEntity = entity;
    }
}
