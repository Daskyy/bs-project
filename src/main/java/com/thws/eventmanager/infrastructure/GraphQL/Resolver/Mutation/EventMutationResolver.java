package com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mutation;

import com.thws.eventmanager.domain.models.Event;
import com.thws.eventmanager.domain.usecases.EventService;
import com.thws.eventmanager.infrastructure.GraphQL.InputModels.EventInput;
import com.thws.eventmanager.infrastructure.GraphQL.InputModels.EventLocationInput;
import com.thws.eventmanager.infrastructure.GraphQL.Models.AddressGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Models.EventGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Models.EventLocationGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain.AdressMapperGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain.EventLocationMapperGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain.EventMapperGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain.UserMapperGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperInputGQL.EventInputMapper;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperInputGQL.EventLocationInputMapper;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperInputGQL.UserInputMapper;
import com.thws.eventmanager.infrastructure.components.persistence.PersistenceManager;
import com.thws.eventmanager.infrastructure.components.persistence.adapter.EventHandler;
import com.thws.eventmanager.infrastructure.components.persistence.entities.EventEntity;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.EventMapper;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import jakarta.persistence.EntityManager;
import org.jetbrains.annotations.NotNull;

import static java.util.stream.Collectors.toList;

public class EventMutationResolver implements GraphQLMutationResolver, GraphQLQueryResolver {
    EventLocationMapperGQL eventLocationMapperGQL = new EventLocationMapperGQL();
    AdressMapperGQL adressMapperGQL = new AdressMapperGQL();
    UserMapperGQL userMapperGQL = new UserMapperGQL();
    EventMapper eventMapper = new EventMapper();
    EventMapperGQL eventMapperGQL = new EventMapperGQL();
    UserInputMapper userInputMapper = new UserInputMapper();
    EventLocationInputMapper EventLocationInputMapper = new EventLocationInputMapper();
    EventInputMapper EventInputMapper = new EventInputMapper();

    public EventGQL createEvent(EventInput input){
        Event event= eventMapperGQL.toModel(EventInputMapper.toModelGQL(input));

        try(EventHandler eventHandler = new EventHandler()){
            EventService eventService = new EventService(eventHandler);

            EventEntity eventEntity= eventService.createEvent(event);
            event= eventMapper.toModel(eventEntity);
            return eventMapperGQL.toModelGQL(event);
        }
    }

    public EventGQL updateEvent(String id, EventInput input){
        EventLocationMapperGQL eventLocationMapperGQL = new EventLocationMapperGQL();
        AdressMapperGQL adressMapperGQL = new AdressMapperGQL();
        UserMapperGQL userMapperGQL = new UserMapperGQL();
        EventMapper eventMapper = new EventMapper();
        EventMapperGQL eventMapperGQL = new EventMapperGQL();



        try(EventHandler eventHandler = new EventHandler()){
            EventService eventService = new EventService(eventHandler);

            EventEntity loaded= eventHandler.findById(Long.parseLong(id)).orElseThrow(); //todo wie damit umgehen

           //TODO

            return null;
        }
    }

    public boolean deleteEvent(String id){
        try(EventHandler eventHandler = new EventHandler()){
            EventService eventService = new EventService(eventHandler);
            eventHandler.deleteById(Long.parseLong(id));
            return true;
        }
    }

}
