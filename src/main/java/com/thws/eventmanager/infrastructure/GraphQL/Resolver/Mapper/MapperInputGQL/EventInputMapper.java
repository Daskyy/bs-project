package com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperInputGQL;

import com.thws.eventmanager.infrastructure.GraphQL.InputModels.EventInput;
import com.thws.eventmanager.infrastructure.GraphQL.InputModels.UserInput;
import com.thws.eventmanager.infrastructure.GraphQL.Models.EventGQL;

public class EventInputMapper {

    public EventGQL toModelGQL(EventInput input){
        UserInputMapper userInputMapper = new UserInputMapper();
        EventLocationInputMapper EventLocationInputMapper = new EventLocationInputMapper();
        UserInputMapper UserInputMapper = new UserInputMapper();
        EventGQL eventGQL = new EventGQL();
        eventGQL.setName(input.getName());
        eventGQL.setDescription(input.getDescription());
        eventGQL.setStartDate(input.getStartDate());
        eventGQL.setEndDate(input.getEndDate());
        eventGQL.setTicketCount(input.getTicketCount());
        eventGQL.setTicketsSold(input.getTicketsSold());
        eventGQL.setMaxTicketsPerUser(input.getMaxTicketsPerUser());

        eventGQL.setArtists(input.getArtists().stream().map(UserInputMapper::toModelGQL).toList());
        eventGQL.setLocation(EventLocationInputMapper.toModelGQL(input.getLocation()));
        eventGQL.setBlockList(input.getBlockList().stream().map(UserInputMapper::toModelGQL).toList());
        return eventGQL;
    }
}
