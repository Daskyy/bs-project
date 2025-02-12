package com.thws.eventmanager.domain.models;
import com.thws.eventmanager.domain.exceptions.InvalidEventException;
import jakarta.persistence.*;

import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class Event implements Model {
    private long id = -1;
    private String name;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private long ticketCount;
    private long ticketsSold;
    private int maxTicketsPerUser;
    private List<User> artists;
    private EventLocation location;
    private List<User> blockList; // List of User IDs that are blocked from buying tickets for this event
    private long ticketPrice;

    public Event(String name, String description, long ticketCount, long ticketsSold, int maxTicketsPerUser, List<User> artists, EventLocation location,List<User> blockList, LocalDateTime startDate, LocalDateTime endDate, int ticketPrice) {
        this.name = name;
        this.description = description;
        this.ticketCount = ticketCount;
        this.ticketsSold = ticketsSold;
        this.maxTicketsPerUser = maxTicketsPerUser;
        this.artists = artists;
        this.location = location;
        this.blockList= blockList;
        this.startDate = startDate;
        this.endDate = endDate;
        this.ticketPrice = ticketPrice;
    }
    public Event(){
        this.artists= new ArrayList<>();
        this.blockList= new ArrayList<>();
    }

    public long getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(long ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public EventLocation getLocation() {
        return location;
    }

    public void setLocation(EventLocation location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
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

    public void setArtists(List<User> artists) {
        this.artists = artists;
    }

    public void setMaxTicketsPerUser(int maxTicketsPerUser) {
        this.maxTicketsPerUser = maxTicketsPerUser;
    }

    public void setBlockList(List<User> blockList) {
        this.blockList = blockList;
    }

    public List<User> getArtists() {
        return artists;
    }

    public List<User> getBlockList() {
        return blockList;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void addArtist(User artist) {
        if (artist == null) {
            throw new IllegalArgumentException("Artist cannot be null.");
        }

        if (artist.getPermission() != Permission.ARTIST) {
            throw new InvalidEventException("Only users with ARTIST permission can be added as artists.");
        }

        if (this.artists.contains(artist)) {
            throw new InvalidEventException("This artist is already part of the event.");
        }

        this.artists.add(artist);
    }

    public void removeArtist(User artist) {
        if (artist == null) {
            throw new IllegalArgumentException("Artist cannot be null.");
        }

        if (!this.artists.contains(artist)) {
            throw new InvalidEventException("The artist is not part of this event.");
        }

        this.artists.remove(artist);
    }

    public void blockUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }

        if (this.blockList.contains(user)) {
            throw new InvalidEventException("User is already blocked for this event.");
        }

        this.blockList.add(user);
    }

    public void unblockUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }

        if (!this.blockList.contains(user)) {
            throw new InvalidEventException("User is not blocked for this event.");
        }

        this.blockList.remove(user);
    }

    public boolean isBlocked(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }
        return this.blockList.contains(user);
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

    public int getTicketsLeft() {
        return (int) (ticketCount - ticketsSold);
    }
}
