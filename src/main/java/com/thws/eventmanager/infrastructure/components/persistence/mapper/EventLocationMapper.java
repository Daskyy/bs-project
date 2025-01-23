package com.thws.eventmanager.infrastructure.components.persistence.mapper;

import com.thws.eventmanager.domain.models.EventLocation;
import com.thws.eventmanager.infrastructure.components.persistence.entities.EventLocationEntity;

public class EventLocationMapper extends Mapper<EventLocation, EventLocationEntity> {
    @Override
    public EventLocation toModel(EventLocationEntity eventLocationE){
        EventLocation eventLocation = new EventLocation();
        AddressMapper addressMapper = new AddressMapper();

        eventLocation.setAddress(addressMapper.toModel(eventLocationE.getAddress()));
        eventLocation.setCapacity(eventLocationE.getCapacity());
        eventLocation.setName(eventLocationE.getName());
        return eventLocation;
    }

    @Override
    public EventLocationEntity toEntity(EventLocation eventLocation) {
        EventLocationEntity eventLocationE = new EventLocationEntity();
        AddressMapper addressMapper = new AddressMapper();

        eventLocationE.setAddress(addressMapper.toEntity(eventLocation.getAddress()));
        eventLocationE.setCapacity(eventLocation.getCapacity());
        eventLocationE.setName(eventLocation.getName());
        return eventLocationE;
    }
}
