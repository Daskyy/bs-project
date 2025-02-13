package com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mutation;

import com.thws.eventmanager.domain.models.Event;
import com.thws.eventmanager.domain.services.models.EventService;
import com.thws.eventmanager.domain.services.models.TicketService;
import com.thws.eventmanager.domain.services.models.UserService;
import com.thws.eventmanager.domain.services.other.TicketPurchaseUseCaseService;
import com.thws.eventmanager.infrastructure.GraphQL.InputModels.EventInput;
import com.thws.eventmanager.infrastructure.GraphQL.Models.EventGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain.AdressMapperGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain.EventLocationMapperGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain.EventMapperGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain.UserMapperGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperInputGQL.EventInputMapper;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperInputGQL.EventLocationInputMapper;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperInputGQL.UserInputMapper;
import com.thws.eventmanager.infrastructure.components.persistence.adapter.EventHandler;
import com.thws.eventmanager.infrastructure.components.persistence.adapter.UserHandler;
import com.thws.eventmanager.infrastructure.components.persistence.entities.EventEntity;
import com.thws.eventmanager.infrastructure.components.persistence.entities.UserEntity;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.EventMapper;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.TicketMapper;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.UserMapper;
import graphql.kickstart.tools.GraphQLMutationResolver;

public class EventMutationResolver implements GraphQLMutationResolver {
    EventLocationMapperGQL eventLocationMapperGQL = new EventLocationMapperGQL();
    AdressMapperGQL adressMapperGQL = new AdressMapperGQL();
    UserMapperGQL userMapperGQL = new UserMapperGQL();
    EventMapper eventMapper = new EventMapper();
    EventMapperGQL eventMapperGQL = new EventMapperGQL();
    UserInputMapper userInputMapper = new UserInputMapper();
    EventLocationInputMapper EventLocationInputMapper = new EventLocationInputMapper();
    EventInputMapper EventInputMapper = new EventInputMapper();
    EventService eventService = new EventService();
    UserService userService = new UserService();
    UserMapper userMapper = new UserMapper();
    TicketMapper ticketMapper = new TicketMapper();
    TicketPurchaseUseCaseService ticketPurchaseUseCaseService = new TicketPurchaseUseCaseService();
    TicketService ticketService = new TicketService();
    public EventGQL createEvent(EventInput input){
        Event event= eventMapperGQL.toModel(EventInputMapper.toModelGQL(input));

        EventService eventService = new EventService();

        EventEntity eventEntity= eventService.createEvent(event);
        event= eventMapper.toModel(eventEntity);
        return eventMapperGQL.toModelGQL(event);

    }

    public EventGQL updateEvent(String id, EventInput input){

        try(EventHandler eventHandler = new EventHandler(); UserHandler userHandler = new UserHandler()){
            EventService eventService = new EventService();
            EventEntity loaded= eventService.getEventById(Long.parseLong(id)).orElseThrow(); //todo wie damit umgehen

            Event event= eventMapper.toModel(loaded);
            if(input.getName()!=null) event.setName(input.getName());
            if(input.getDescription()!=null) event.setDescription(input.getDescription());
            if(input.getTicketCount()!=-1) event.setTicketCount(input.getTicketCount());
            if(input.getTicketsSold()!=-1) event.setTicketsSold(input.getTicketsSold());
            if(input.getMaxTicketsPerUser()!=-1) event.setMaxTicketsPerUser(input.getMaxTicketsPerUser());

            if(input.getArtists()!=null) event.setArtists(userMapperGQL.toUserGQLList(input.getArtists()).stream().map(userMapperGQL::toModel).toList());

            EventEntity eventEnity= eventService.createEvent(event);
            event= eventMapper.toModel(eventEnity);
            return eventMapperGQL.toModelGQL(event);
        }
    }

    public EventGQL deleteEvent(String id){
        EventService eventService = new EventService();
        EventEntity ee= eventService.getEventById(Long.parseLong(id)).orElseThrow();

        Event event = eventMapper.toModel(ee);
        eventService.refundEvent(event);

        eventService.deleteEvent(eventMapper.toModel(ee));

        return eventMapperGQL.toModelGQL(eventMapper.toModel(ee));


    }

    public EventGQL blockUser(String userId, String eventId) {
        UserEntity userEntity = userService.getUserById(Long.parseLong(userId)).orElseThrow();
        EventEntity eventEntity = eventService.getEventById(Long.parseLong(eventId)).orElseThrow();
        return eventMapperGQL.toModelGQL(
                eventMapper.toModel(
                        eventService.blockUser(
                                eventMapper.toModel(eventEntity),
                                        userMapper.toModel(
                                                userEntity
                ))));
    }

}