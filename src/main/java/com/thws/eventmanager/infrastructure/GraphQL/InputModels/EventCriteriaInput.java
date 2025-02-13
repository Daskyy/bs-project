package com.thws.eventmanager.infrastructure.GraphQL.InputModels;

import java.util.ArrayList;
import java.util.List;

public class EventCriteriaInput {
    private String description;
    private String name;
    private String location_id;
    private Integer maxticketsperuser;
    private Integer ticketcount;
    private Integer ticketprice;
    private Integer ticketssold;
    private String artists_id;

    public EventCriteriaInput() {}

    public int getMaxticketsperuser() {
        return maxticketsperuser;
    }

    public void setMaxticketsperuser(Integer maxticketsperuser) {
        this.maxticketsperuser = maxticketsperuser;
    }

    public void setTicketcount(Integer ticketcount) {
        this.ticketcount = ticketcount;
    }

    public void setTicketprice(Integer ticketprice) {
        this.ticketprice = ticketprice;
    }

    public void setTicketssold(Integer ticketssold) {
        this.ticketssold = ticketssold;
    }

    public String getArtists_id() {
        return artists_id;
    }

    public void setArtists_id(String artists_id) {
        this.artists_id = artists_id;
    }

    public void setMaxticketsperuser(int maxticketsperuser) {
        this.maxticketsperuser = maxticketsperuser;
    }

    public int getTicketcount() {
        return ticketcount;
    }

    public void setTicketcount(int ticketcount) {
        this.ticketcount = ticketcount;
    }

    public int getTicketprice() {
        return ticketprice;
    }

    public void setTicketprice(int ticketprice) {
        this.ticketprice = ticketprice;
    }

    public int getTicketssold() {
        return ticketssold;
    }

    public void setTicketssold(int ticketssold) {
        this.ticketssold = ticketssold;
    }

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Object> getValues() {
        List<Object> values = new ArrayList<>();
        if (description != null && !description.isBlank()) {
            values.add(description);
        }
        if (name != null && !name.isBlank()) {
            values.add(name);
        }
        if (location_id != null && !location_id.isBlank()) {
            values.add(location_id);
        }
        if (maxticketsperuser != null) {
            values.add(maxticketsperuser);
        }
        if (ticketcount != null) {
            values.add(ticketcount);
        }
        if (ticketprice != null) {
            values.add(ticketprice);
        }
        if (ticketssold != null) {
            values.add(ticketssold);
        }
        if (artists_id != null && !artists_id.isBlank()) {
            values.add(artists_id);
        }
        return values;
    }

    public List<String> getCriteria() {
        List<String> criteria = new ArrayList<>();
        if (description != null && !description.isBlank()) {
            criteria.add("description");
        }
        if (name != null && !name.isBlank()) {
            criteria.add("name");
        }
        if (location_id != null && !location_id.isBlank()) {
            criteria.add("location_id");
        }
        if (maxticketsperuser != null) {
            criteria.add("maxticketsperuser");
        }
        if (ticketcount != null) {
            criteria.add("ticketcount");
        }
        if (ticketprice != null) {
            criteria.add("ticketprice");
        }
        if (ticketssold != null) {
            criteria.add("ticketssold");
        }
        if(artists_id != null && !artists_id.isBlank()) {
            criteria.add("artists_id");
        }

        return criteria;
    }
}
