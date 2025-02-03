package com.thws.eventmanager.infrastructure.components.persistence.mapper;

import com.thws.eventmanager.domain.models.EventLocation;
import com.thws.eventmanager.infrastructure.components.persistence.entities.EventLocationEntity;

public class EventLocationMapper extends Mapper<EventLocation, EventLocationEntity> {
    AddressMapper addressMapper = new AddressMapper();
    @Override
    public EventLocation toModel(EventLocationEntity entity){
        EventLocation eventLocation = new EventLocation();

        eventLocation.setAddress(addressMapper.toModel(entity.getAddress()));
        eventLocation.setCapacity(entity.getCapacity());
        eventLocation.setName(entity.getName());
        eventLocation.setId(entity.getId());
        return eventLocation;
    }

    @Override
    public EventLocationEntity toEntity(EventLocation eventLocation) {
        EventLocationEntity entity = new EventLocationEntity();

        entity.setAddress(addressMapper.toEntity(eventLocation.getAddress()));
        entity.setCapacity(eventLocation.getCapacity());
        entity.setName(eventLocation.getName());

        if(eventLocation.getId() != -1) {
            entity.setId(eventLocation.getId());
        }

        return entity;
    }
}
