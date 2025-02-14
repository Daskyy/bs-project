package com.thws.eventmanager.infrastructure.components.persistence.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "events")
public class EventEntity implements PersistenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column(nullable = false)
    private long ticketCount;

    @Column(nullable = false)
    private long ticketsSold;

    @Column(nullable = false)
    private int maxTicketsPerUser;

    @Column(nullable = false)
    private long ticketPrice;

    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "location_id", nullable = false)
    private EventLocationEntity location;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "event_artists",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserEntity> artists;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @JoinTable(
            name = "event_blocked_users",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserEntity> blockList;

    public EventEntity(){}

    public EventLocationEntity getLocation() {
        return location;
    }

    public void setLocation(EventLocationEntity location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getStartDate() { return startDate; }

    public LocalDateTime getEndDate() { return endDate; }

    public long getTicketCount() {
        return ticketCount;
    }

    public long getTicketsSold() {
        return ticketsSold;
    }

    public int getMaxTicketsPerUser() {
        return maxTicketsPerUser;
    }

    public boolean isSoldOut() {
        return ticketCount == ticketsSold;
    }

    public boolean isAvailable() {
        return ticketCount > ticketsSold;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTicketCount(long ticketCount) {
        this.ticketCount = ticketCount;
    }

    public void setTicketsSold(long ticketsSold) {
        this.ticketsSold = ticketsSold;
    }

    public long getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(long ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public void setMaxTicketsPerUser(int maxTicketsPerUser) {
        this.maxTicketsPerUser = maxTicketsPerUser;
    }

    public List<UserEntity> getArtists() {
        return artists;
    }

    public List<UserEntity> getBlockList() {
        return blockList;
    }

    public void setArtists(List<UserEntity> list) {
        this.artists = list;
    }

    public void setBlockList(List<UserEntity> list) {
        this.blockList = list;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "EventEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", ticketCount=" + ticketCount +
                ", ticketsSold=" + ticketsSold +
                ", maxTicketsPerUser=" + maxTicketsPerUser +
                ", location=" + location +
                ", artists=" + artists +
                ", blockList=" + blockList +
                '}';
    }

    public long getId() {
        return id;
    }
}
