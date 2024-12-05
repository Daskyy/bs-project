package com.thws.eventmanager.domain.models;

public class Event {
    private long id;
    private String name;
    private String description;
    private long ticketCount;
    private long ticketsSold;
    private int maxTicketsPerUser;
    private User[] artists;
    private Address location;


    public Event(long id, String name, String description, long ticketCount, long ticketsSold, User[] artists, int maxTicketsPerUser) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ticketCount = ticketCount;
        this.ticketsSold = ticketsSold;
        this.artists = artists;
        this.maxTicketsPerUser = maxTicketsPerUser;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public long getTicketCount() {
        return ticketCount;
    }

    public long getTicketsSold() {
        return ticketsSold;
    }

    public User[] getArtists() {
        return artists;
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

    public void setId(long id) {
        this.id = id;
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

    public void setArtists(User[] artists) {
        this.artists = artists;
    }

    public void setMaxTicketsPerUser(int maxTicketsPerUser) {
        this.maxTicketsPerUser = maxTicketsPerUser;
    }
}
