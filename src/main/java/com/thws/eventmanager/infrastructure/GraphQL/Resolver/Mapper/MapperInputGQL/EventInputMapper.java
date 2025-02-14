package com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperInputGQL;

import com.thws.eventmanager.infrastructure.GraphQL.InputModels.EventInput;
import com.thws.eventmanager.infrastructure.GraphQL.InputModels.UserInput;
import com.thws.eventmanager.infrastructure.GraphQL.Models.EventGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Models.UserGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain.EventLocationMapperGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain.UserMapperGQL;
import com.thws.eventmanager.infrastructure.components.persistence.adapter.EventHandler;
import com.thws.eventmanager.infrastructure.components.persistence.adapter.EventLocationHandler;
import com.thws.eventmanager.infrastructure.components.persistence.adapter.UserHandler;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.EventLocationMapper;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.UserMapper;

import java.util.ArrayList;
import java.util.List;

public class EventInputMapper {
    UserInputMapper userInputMapper = new UserInputMapper();
    UserMapper  userMapper = new UserMapper();
    UserMapperGQL userMapperGQL = new UserMapperGQL();
    EventLocationInputMapper EventLocationInputMapper = new EventLocationInputMapper();
    EventLocationMapperGQL eventLocationMapperGQL = new EventLocationMapperGQL();
    EventLocationMapper eventLocationMapper = new EventLocationMapper();




    public EventGQL toModelGQL(EventInput input) {
        try (EventHandler e = new EventHandler(); UserHandler u= new UserHandler(); EventLocationHandler el = new EventLocationHandler()) {
            EventGQL eventGQL = new EventGQL();
            eventGQL.setName(input.getName());
            eventGQL.setDescription(input.getDescription());
            eventGQL.setStartDate(input.getStartDate());
            eventGQL.setEndDate(input.getEndDate());
            eventGQL.setTicketCount(input.getTicketCount());
            eventGQL.setTicketsSold(input.getTicketsSold());
            eventGQL.setMaxTicketsPerUser(input.getMaxTicketsPerUser());


            List<String> aList= input.getArtists();
            List<UserGQL> aListGQL = userMapperGQL.toUserGQLList(aList);

            eventGQL.setArtists(aListGQL);
            eventGQL.setLocation(eventLocationMapperGQL.
                    toModelGQL(eventLocationMapper.
                            toModel(el.findById(Long.parseLong(input.getLocation())).get())));

            List<String> bList= input.getBlockList();
            List<UserGQL> bListGQL = userMapperGQL.toUserGQLList(bList);

            eventGQL.setBlockList(bListGQL);
            eventGQL.setTicketPrice(input.getTicketPrice());
            eventGQL.setId("-1");
            return eventGQL;
        }
    }
}
