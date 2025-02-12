package com.thws.eventmanager.infrastructure.GraphQL.InputModels;

import java.util.Collections;
import java.util.List;

public class EventInput {
    private String name;
    private String description;
    private String startDate;
    private String endDate;
    private int ticketCount;
    private int ticketsSold;
    private int maxTicketsPerUser;
    private List<UserInput> artists;
    private EventLocationInput location;
    private List<UserInput> blockList;
    private long ticketPrice;

    public EventInput() {}

    public long getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(long ticketPrice) {
        this.ticketPrice = ticketPrice;
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

    public List<UserInput> getArtists() {
        return artists;
    }

    public void setArtists(List<UserInput> artists) {
        this.artists = artists;
    }

    public List<UserInput> getBlockList() {
        return blockList != null ? blockList : Collections.emptyList();
    }

    public void setBlockList(List<UserInput> blockList) {
        this.blockList = blockList;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public EventLocationInput getLocation() {
        return location;
    }

    public void setLocation(EventLocationInput location) {
        this.location = location;
    }
}
