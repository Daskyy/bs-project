package com.thws.eventmanager.infrastructure.GraphQL.Resolver.Query;

import com.thws.eventmanager.domain.models.Event;
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
        return null; //TODO wie gehen wir mit fehlern um?
    }
    public List<EventGQL> events(EventCriteriaInput criteria){
//        EventCrit
//        try(PersistenceManager persistenceManager = PersistenceManager.create())
//        {
//            EntityManager entityManager= persistenceManager.getEntityManager();
//            EventHandler e= new EventHandler(entityManager);
//            EventSearchService eventSearchService = new EventSearchService(e);
//
//
//            eventSearchService.searchEvents()
//        }
    return null;
    }

    public static void main(String[] args) {
        EventQueryResolver e = new EventQueryResolver();
        EventGQL ee= e.event("121");
        System.out.println(ee.getName());

    }
}

