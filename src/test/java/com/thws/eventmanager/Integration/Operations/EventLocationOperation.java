package com.thws.eventmanager.Integration.Operations;

import com.thws.eventmanager.infrastructure.GraphQL.Models.EventLocationGQL;

public class EventLocationOperation extends AbstractAPIOperation {

    public EventLocationGQL getEventLocation(String id) throws Exception {
        String query = String.format("query getEventLocation { eventLocation(id: \"%s\") { id name capacity } }", id);
        return executeQuery(query, "eventLocation", EventLocationGQL.class);
    }

    public EventLocationGQL[] getEventLocations() throws Exception {
        String query = "query getEventLocations { eventLocations(criteria: {}) { id name capacity } }";
        return executeQuery(query, "eventLocations", EventLocationGQL[].class);
    }
} 