package com.thws.eventmanager.infrastructure.components.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "event_locations")
public class EventLocationEntity implements PersistenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
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

    @Override
    public String toString() {
        return "EventLocationEntity{" +
                "id=" + id +
                ", addressEntity=" + addressEntity +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                '}';
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

}
