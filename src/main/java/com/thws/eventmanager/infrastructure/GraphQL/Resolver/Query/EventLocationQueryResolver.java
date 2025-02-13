package com.thws.eventmanager.infrastructure.GraphQL.Resolver.Query;

import com.thws.eventmanager.domain.services.models.EventLocationService;
import com.thws.eventmanager.infrastructure.GraphQL.InputModels.EventLocationCriteriaInput;
import com.thws.eventmanager.infrastructure.GraphQL.Models.EventLocationGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain.EventLocationMapperGQL;
import com.thws.eventmanager.infrastructure.components.persistence.entities.EventLocationEntity;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.EventLocationMapper;
import graphql.kickstart.tools.GraphQLQueryResolver;

import java.util.List;

public class EventLocationQueryResolver implements GraphQLQueryResolver {
    EventLocationMapper mapper = new EventLocationMapper();
    EventLocationMapperGQL mapperGQL = new EventLocationMapperGQL();
    EventLocationService eventLocationService = new EventLocationService();
    /*
        String id="-1";
    AddressInput address;
    String name;
    int capacity;

     */

    public EventLocationGQL eventLocation(String id){
        EventLocationEntity eventLocationEntity = eventLocationService.getLocationById(Long.parseLong(id)).orElse(null);
        if(eventLocationEntity == null) return null;
        return mapperGQL.toModelGQL(mapper.toModel(eventLocationEntity));
    }

    public List<EventLocationGQL> eventLocations(EventLocationCriteriaInput criteria){
        if (criteria == null) {
            return eventLocationService.getAllEventLocations().stream()
                    .map(mapper::toModel)
                    .map(mapperGQL::toModelGQL)
                    .toList();
        }

        List<String> criteriaList = criteria.getCriteria();
        List<Object> valuesList = criteria.getValues();
        if (criteriaList.size() != valuesList.size()) {
            throw new IllegalArgumentException("Criteria and values lists must have the same size");
        } else {
            return eventLocationService.getAllEventLocations(criteriaList, valuesList).stream()
                    .map(mapper::toModel)
                    .map(mapperGQL::toModelGQL)
                    .toList();
        }
    }
}
