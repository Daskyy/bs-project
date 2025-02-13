package com.thws.eventmanager.infrastructure.GraphQL.Resolver.Query;

import com.thws.eventmanager.domain.models.Event;
import com.thws.eventmanager.domain.services.models.EventSearchService;
import com.thws.eventmanager.domain.services.models.EventService;
import com.thws.eventmanager.infrastructure.GraphQL.InputModels.EventCriteriaInput;
import com.thws.eventmanager.infrastructure.GraphQL.Models.EventGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain.EventMapperGQL;
import com.thws.eventmanager.infrastructure.components.persistence.PersistenceManager;
import com.thws.eventmanager.infrastructure.components.persistence.adapter.EventHandler;
import com.thws.eventmanager.infrastructure.components.persistence.entities.EventEntity;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.EventMapper;
import graphql.kickstart.tools.GraphQLQueryResolver;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class EventQueryResolver implements GraphQLQueryResolver {

    EventService eventService = new EventService();
    EventMapper eventMapper = new EventMapper();
    EventMapperGQL eventMapperGQL = new EventMapperGQL();


    public EventGQL event(String id){
        try(EventHandler e = new EventHandler())
        {
        Optional<EventEntity> optinoal = e.findById(Long.parseLong(id));
        if(optinoal.isPresent()){
            EventEntity eventEntity =e.findById(Long.parseLong(id)).get();
            Event E= new EventMapper().toModel(eventEntity);
            EventGQL EGQL= new EventMapperGQL().toModelGQL(E);
            return EGQL;
        }
        }//catch ()
        return null; //TODO wie gehen wir mit fehlern um? | lasse laufe wird schon -david
    }

    public List<EventGQL> events(EventCriteriaInput criteria) {
        if(criteria == null) {
            return eventService.getAllEvents().stream()
                    .map(eventMapper::toModel)
                    .map(eventMapperGQL::toModelGQL)
                    .toList();
        }

        List<String> criteriaList = criteria.getCriteria();
        List<Object> valuesList = criteria.getValues();
        if(criteriaList.size() != valuesList.size()) {
            throw new IllegalArgumentException("Criteria and values lists must have the same size");
        } else {
            return eventService.getAllEvents(criteriaList, valuesList).stream()
                    .map(eventMapper::toModel)
                    .map(eventMapperGQL::toModelGQL)
                    .toList();
        }
    }



}

