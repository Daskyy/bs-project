package com.thws.eventmanager.Integration.Operations;

import com.thws.eventmanager.infrastructure.GraphQL.Models.EventGQL;
import com.thws.eventmanager.infrastructure.GraphQL.InputModels.EventInput;

public class EventOperation extends AbstractAPIOperation {

    public EventGQL createEvent(EventInput input) throws Exception {
        String mutation = String.format(
            "mutation createEvent { " +
            "  createEvent(input: {name: \\\"%s\\\", description: \\\"%s\\\", startDate: \\\"%s\\\", endDate: \\\"%s\\\", ticketCount: %d, ticketsSold: %d, maxTicketsPerUser: %d, artists: [%s], location: \\\"%s\\\", blockList: [%s], ticketPrice: %d}) { " +
            "    id name description startDate endDate ticketCount ticketsSold maxTicketsPerUser ticketPrice " +
            "  }" +
            "}",
            input.getName(), input.getDescription(), input.getStartDate(), input.getEndDate(), input.getTicketCount(), input.getTicketsSold(),
            input.getMaxTicketsPerUser(), formatArtists(input), input.getLocation(), formatBlockList(input), (int)input.getTicketPrice());
        return executeQuery(mutation, "createEvent", EventGQL.class);
    }
    
    private String formatArtists(EventInput input) {
        if (input.getArtists() == null || input.getArtists().isEmpty())
            return "";
        StringBuilder sb = new StringBuilder();
        for (String artist : input.getArtists()) {
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append("\\\"").append(artist).append("\\\"");
        }
        return sb.toString();
    }
    
    private String formatBlockList(EventInput input) {
        if (input.getBlockList() == null || input.getBlockList().isEmpty())
            return "";
        StringBuilder sb = new StringBuilder();
        for (String userId : input.getBlockList()) {
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append("\\\"").append(userId).append("\\\"");
        }
        return sb.toString();
    }
    
    public EventGQL updateEvent(String id, EventInput input) throws Exception {
        String mutation = String.format(
            "mutation updateEvent { " +
            "  updateEvent(id: \\\"%s\\\", input: {name: \\\"%s\\\", description: \\\"%s\\\", startDate: \\\"%s\\\", endDate: \\\"%s\\\", ticketCount: %d, ticketsSold: %d, maxTicketsPerUser: %d, ticketPrice: %d}) { " +
            "    id name description " +
            "  }" +
            "}",
            id, input.getName(), input.getDescription(), input.getStartDate(), input.getEndDate(), input.getTicketCount(), input.getTicketsSold(),
            input.getMaxTicketsPerUser(), (int)input.getTicketPrice());
            
        return executeQuery(mutation, "updateEvent", EventGQL.class);
    }
    
    public EventGQL deleteEvent(String id) throws Exception {
        String mutation = String.format(
            "mutation deleteEvent { " +
            "  deleteEvent(id: \\\"%s\\\") { " +
            "    id " +
            "  }" +
            "}",
            id);
        return executeQuery(mutation, "deleteEvent", EventGQL.class);
    }
    
    public EventGQL blockUser(String eventId, String userId) throws Exception {
        String mutation = String.format(
            "mutation blockUser { " +
            "  blockUser(eventId: \\\"%s\\\", userId: \\\"%s\\\") { " +
            "    id " +
            "    blockList { id } " +
            "  }" +
            "}",
            eventId, userId);
        return executeQuery(mutation, "blockUser", EventGQL.class);
    }
    
    public EventGQL getEvent(String id) throws Exception {
        String query = String.format(
            "query getEvent { " +
            "  event(id: \\\"%s\\\") { id name description }" +
            "}",
            id);
        return executeQuery(query, "event", EventGQL.class);
    }
    
    public EventGQL[] getEvents() throws Exception {
        String query = "query getEvents { " +
                       "  events(criteria: {}, page: {}) { id name description startDate endDate ticketCount ticketsSold maxTicketsPerUser ticketPrice }" +
                       "}";
        return executeQuery(query, "events", EventGQL[].class);
    }
    
    public EventGQL[] trendingEvents() throws Exception {
        String query = "query trendingEvents { " +
                       "  trendingEvents(page: {}) { id name description }" +
                       "}";
        return executeQuery(query, "trendingEvents", EventGQL[].class);
    }
} 