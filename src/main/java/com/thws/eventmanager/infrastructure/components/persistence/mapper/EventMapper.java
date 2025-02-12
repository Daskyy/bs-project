package com.thws.eventmanager.infrastructure.components.persistence.mapper;

import com.thws.eventmanager.domain.models.Event;
import com.thws.eventmanager.infrastructure.components.persistence.entities.EventEntity;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EventMapper extends Mapper<Event, EventEntity> {

    private final UserMapper userMapper = new UserMapper();
    private final EventLocationMapper eventLocationMapper = new EventLocationMapper();

    @Override
    public Event toModel(EventEntity entity) {
        Event event = new Event();

        event.setName(entity.getName());
        event.setDescription(entity.getDescription());
        event.setTicketCount(entity.getTicketCount());
        event.setTicketsSold(entity.getTicketsSold());
        event.setMaxTicketsPerUser(entity.getMaxTicketsPerUser());
        event.setStartDate(entity.getStartDate());
        event.setEndDate(entity.getEndDate());
        event.setArtists(mapList(entity.getArtists(), userMapper::toModel));
        event.setBlockList(mapList(entity.getBlockList(), userMapper::toModel));
        event.setLocation(eventLocationMapper.toModel(entity.getLocation()));
        event.setId(entity.getId());
        event.setTicketPrice(entity.getTicketPrice());

        return event;
    }

    @Override
    public EventEntity toEntity(Event event) {
        EventEntity entity = new EventEntity();

        entity.setName(event.getName());
        entity.setDescription(event.getDescription());
        entity.setTicketCount(event.getTicketCount());
        entity.setTicketsSold(event.getTicketsSold());
        entity.setStartDate(event.getStartDate());
        entity.setEndDate(event.getEndDate());
        entity.setMaxTicketsPerUser(event.getMaxTicketsPerUser());
        entity.setLocation(eventLocationMapper.toEntity(event.getLocation()));
        entity.setArtists(mapList(event.getArtists(), userMapper::toEntity));
        entity.setBlockList(mapList(event.getBlockList(), userMapper::toEntity));
        entity.setTicketPrice(event.getTicketPrice());
//        if(event.getId() != -1) {
//            entity.setId(event.getId());
//        }
        return entity;
    }

    // Generic helper method to map lists (works for both directions)
    private <Mod, Entity> List<Mod> mapList(List<Entity> sourceList, Function<Entity, Mod> mapperFunction) {
        if (sourceList == null) {
            return Collections.emptyList();  // Return an empty list if sourceList is null
        }

        return sourceList.stream()
                .map(mapperFunction)
                .collect(Collectors.toList());
    }
}
