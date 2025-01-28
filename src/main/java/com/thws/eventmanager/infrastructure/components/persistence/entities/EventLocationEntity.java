package com.thws.eventmanager.infrastructure.components.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "eventlocations")
public class EventLocationEntity implements PersistenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    private AddressEntity addressEntity;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int capacity;

    public EventLocationEntity(){}; // default constructor

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
