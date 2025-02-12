package com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain;

import com.thws.eventmanager.domain.models.Event;
import com.thws.eventmanager.infrastructure.GraphQL.Models.EventGQL;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class EventMapperGQL extends Mapper<Event, EventGQL> {
    EventLocationMapperGQL eventLocationMapperGQL = new EventLocationMapperGQL();
    UserMapperGQL userMapperGQL = new UserMapperGQL();
    DateTimeFormatter FORMATTER= DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @Override
    public Event toModel(EventGQL eventGQL){
        if(eventGQL == null){return null;}
        Event event= new Event();
        event.setName(eventGQL.getName());
        event.setDescription(eventGQL.getDescription());
        event.setTicketCount(eventGQL.getTicketCount());
        event.setTicketsSold(eventGQL.getTicketsSold());
        event.setMaxTicketsPerUser(eventGQL.getMaxTicketsPerUser());
        //todo
        event.setArtists((eventGQL.getArtists()).stream().map(userMapperGQL::toModel).collect(Collectors.toList()));

        event.setLocation(eventLocationMapperGQL.toModel(eventGQL.getLocation()));
        event.setBlockList((eventGQL.getBlockList()).stream().map(userMapperGQL::toModel).collect(Collectors.toList()));
        event.setTicketPrice(eventGQL.getTicketPrice());

        event.setStartDate(LocalDateTime.from(FORMATTER.parse(eventGQL.getStartDate())));
        event.setEndDate(LocalDateTime.from(FORMATTER.parse(eventGQL.getEndDate())));


        return event;
    }

    @Override
    public EventGQL toModelGQL(Event event){
        if(event == null){return null;}
        EventGQL gql = new EventGQL();
        gql.setId(String.valueOf(event.getId()));
        gql.setName(event.getName());
        gql.setDescription(event.getDescription());
        gql.setTicketCount((int)event.getTicketCount()); //TODO unschön dass hier gecastet werden muss
        gql.setTicketsSold((int)event.getTicketsSold());
        gql.setMaxTicketsPerUser(event.getMaxTicketsPerUser());
        //todo
        gql.setArtists((event.getArtists()).stream().map(userMapperGQL::toModelGQL).collect(Collectors.toList()));

        gql.setLocation(eventLocationMapperGQL.toModelGQL(event.getLocation()));
        gql.setBlockList((event.getBlockList()).stream().map(userMapperGQL::toModelGQL).collect(Collectors.toList()));
        gql.setTicketPrice(event.getTicketPrice());
        //TODO startDate und endDate
        return gql;
    }


}
