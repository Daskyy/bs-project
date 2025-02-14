package com.thws.eventmanager.Integration.Operations;

import com.thws.eventmanager.infrastructure.GraphQL.InputModels.EventLocationInput;
import com.thws.eventmanager.infrastructure.GraphQL.Models.EventLocationGQL;

public class EventLocationOperation extends AbstractAPIOperation {

    public EventLocationGQL getEventLocation(String id) throws Exception {
        String query = String.format("query eventLocation { eventLocation(id: \"%s\") { id name capacity } }", id);
        return executeQuery(query, "eventLocation", EventLocationGQL.class);
    }

    public EventLocationGQL[] getEventLocations() throws Exception {
        String query = "query eventLocations { eventLocations(criteria: {}) { id name capacity } }";
        return executeQuery(query, "eventLocations", EventLocationGQL[].class);
    }
    public EventLocationGQL createEventLocation(EventLocationInput eventLocation) throws Exception {
        String mutation = String.format(
            "mutation createEventLocation { " +
            "  createEventLocation(input: {name: \"%s\", capacity: %d, address: {street: \"%s\", no: %d, city: \"%s\", zipCode: %d, country: \"%s\"}}) { " +
            "    id name capacity " +
            "  }" +
            "}",
            eventLocation.getName(), eventLocation.getCapacity(), eventLocation.getAddress().getStreet(), eventLocation.getAddress().getNo(),
            eventLocation.getAddress().getCity(), eventLocation.getAddress().getZipCode(), eventLocation.getAddress().getCountry());
        return executeQuery(mutation, "createEventLocation", EventLocationGQL.class);
    }
} 