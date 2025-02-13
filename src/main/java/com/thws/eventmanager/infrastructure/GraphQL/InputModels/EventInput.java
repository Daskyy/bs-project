package com.thws.eventmanager.infrastructure.GraphQL.InputModels;

import java.util.Collections;
import java.util.List;

public class EventInput {
    private String name;
    private String description;
    private String startDate;                   //muss dieses Format haben: 2031-02-20T10:00:00
    private String endDate;
    private int ticketCount =-1;
    private int ticketsSold =-1;
    private int maxTicketsPerUser =-1;
    private List<String> artists;
    private String location;
    private List<String> blockList;
    private long ticketPrice =-1;

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

    public List<String> getArtists() {
        return artists !=null ? artists : Collections.emptyList();
    }

    public void setArtists(List<String> artists) {
        this.artists = artists;
    }

    public List<String> getBlockList() {
        return blockList != null ? blockList : Collections.emptyList();
    }

    public void setBlockList(List<String> blockList) {
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
