package com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain;

import com.thws.eventmanager.domain.models.EventLocation;
import com.thws.eventmanager.infrastructure.GraphQL.Models.EventLocationGQL;

public class EventLocationMapperGQL extends Mapper<EventLocation, EventLocationGQL> {
    AdressMapperGQL addressMapperGQL = new AdressMapperGQL();
    @Override
    public EventLocation toModel(EventLocationGQL eventLocationGQL){
        if (eventLocationGQL == null) return null;
        EventLocation el = new EventLocation();
        // id: null => generiert
        el.setAddress(addressMapperGQL.toModel(eventLocationGQL.getAddress()));
        el.setName(eventLocationGQL.getName());
        el.setCapacity(eventLocationGQL.getCapacity());
        return el;
    }

    @Override
    public EventLocationGQL toModelGQL(EventLocation eventlocation){
        if (eventlocation == null) return null;
        EventLocationGQL gql = new EventLocationGQL();
        gql.setId(String.valueOf(eventlocation.getId()));
        gql.setAddress(addressMapperGQL.toModelGQL(eventlocation.getAddress()));
        gql.setName(eventlocation.getName());
        gql.setCapacity(eventlocation.getCapacity());
        return gql;
    }
}
