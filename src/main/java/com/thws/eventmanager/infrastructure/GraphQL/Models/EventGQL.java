package com.thws.eventmanager.infrastructure.GraphQL.Models;

import java.util.List;

public class EventGQL implements GQLModel {
    private String id;
    private String name;
    private String description;
    private String startDate;
    private String endDate;
    private int ticketCount;
    private int ticketsSold;
    private int maxTicketsPerUser;
    private List<UserGQL> artists;
    private EventLocationGQL location;
    private List<UserGQL> blockList;
    private long ticketPrice;

    public EventGQL() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(int ticketCount) {
        this.ticketCount = ticketCount;
    }

    public int getTicketsSold() {
        return ticketsSold;
    }

    public void setTicketsSold(int ticketsSold) {
        this.ticketsSold = ticketsSold;
    }

    public int getMaxTicketsPerUser() {
        return maxTicketsPerUser;
    }

    public void setMaxTicketsPerUser(int maxTicketsPerUser) {
        this.maxTicketsPerUser = maxTicketsPerUser;
    }

    public List<UserGQL> getArtists() {
        return artists;
    }

    public void setArtists(List<UserGQL> artists) {
        this.artists = artists;
    }

    public EventLocationGQL getLocation() {
        return location;
    }

    public void setLocation(EventLocationGQL location) {
        this.location = location;
    }

    public List<UserGQL> getBlockList() {
        return blockList;
    }

    public void setBlockList(List<UserGQL> blockList) {
        this.blockList = blockList;
    }

    public long getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(long ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}
