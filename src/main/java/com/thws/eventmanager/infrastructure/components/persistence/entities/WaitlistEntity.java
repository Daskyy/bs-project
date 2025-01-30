package com.thws.eventmanager.infrastructure.components.persistence.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// TODO: create a table for this entity
@Entity
@Table(name = "waitlist")
public class WaitlistEntity implements PersistenceEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "event_id", nullable = false, unique = true)
    private EventEntity event;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "waitlist_id")
    private List<UserEntity> userEntities = new ArrayList<>();

    public WaitlistEntity() {}

    public WaitlistEntity(EventEntity event) {
        if (event == null) {
            throw new IllegalArgumentException("Event cannot be null");
        }
        this.event = event;
    }

    public Long getId() {
        return id;
    }

    public EventEntity getEvent() {
        return event;
    }

    public void setEvent(EventEntity event) {
        this.event = event;
    }

    public void setUserEntities(List<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }

    public List<UserEntity> getUsers() {
        return Collections.unmodifiableList(userEntities);
    }

    public void addUser(UserEntity userEntity) {
        if (userEntity == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        userEntities.add(userEntity);
    }

    public boolean removeUser(UserEntity userEntity) {
        return userEntities.remove(userEntity);
    }

    public UserEntity getFirstUser() {
        return userEntities.isEmpty() ? null : userEntities.getFirst();
    }
}
