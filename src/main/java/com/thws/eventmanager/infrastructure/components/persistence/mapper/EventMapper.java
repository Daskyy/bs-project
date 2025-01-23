package com.thws.eventmanager.infrastructure.components.persistence.mapper;

import com.thws.eventmanager.domain.models.Event;
import com.thws.eventmanager.domain.models.Model;
import com.thws.eventmanager.infrastructure.components.persistence.entities.EventEntity;
import com.thws.eventmanager.infrastructure.components.persistence.entities.PersistenceEntity;

import java.util.List;
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

        // Use the generic mapList method for both artists and blocklist
        event.setArtists(mapList(entity.getArtists(), userMapper));
        event.setBlockList(mapList(entity.getBlockList(), userMapper));

        event.setLocation(eventLocationMapper.toModel(entity.getLocation()));

        return event;
    }

    @Override
    public EventEntity toEntity(Event event) {
        EventEntity entity = new EventEntity();

        entity.setName(event.getName());
        entity.setDescription(event.getDescription());
        entity.setTicketCount(event.getTicketCount());
        entity.setTicketsSold(event.getTicketsSold());
        entity.setMaxTicketsPerUser(event.getMaxTicketsPerUser());

        // Use the generic mapList method for both artists and blocklist
        entity.setArtists(mapListToEntity(event.getArtists(), userMapper));
        entity.setBlockList(mapListToEntity(event.getBlockList(), userMapper));

        return entity;
    }

    // Generic helper method to map lists (works for any type of model and entity)
    private <Mod extends Model, Entity extends PersistenceEntity> List<Mod> mapList(List<Entity> sourceList, Mapper<Mod, Entity> mapper) {
        return sourceList.stream()
                .map(mapper::toModel)
                .collect(Collectors.toList());
    }

    // Reverse method for entity mapping if needed
    private <Mod extends Model, Entity extends PersistenceEntity> List<Entity> mapListToEntity(List<Mod> sourceList, Mapper<Mod, Entity> mapper) {
        return sourceList.stream()
                .map(mapper::toEntity)
                .collect(Collectors.toList());
    }
}
