package com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mutation;

import com.thws.eventmanager.domain.services.models.AddressService;
import com.thws.eventmanager.domain.services.models.EventLocationService;
import com.thws.eventmanager.infrastructure.GraphQL.InputModels.EventLocationInput;
import com.thws.eventmanager.infrastructure.GraphQL.Models.EventLocationGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain.AddressMapperGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain.EventLocationMapperGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperInputGQL.EventLocationInputMapper;
import com.thws.eventmanager.infrastructure.components.persistence.entities.AddressEntity;
import com.thws.eventmanager.infrastructure.components.persistence.entities.EventLocationEntity;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.AddressMapper;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.EventLocationMapper;
import graphql.kickstart.tools.GraphQLMutationResolver;

public class EventLocationMutationResolver implements GraphQLMutationResolver {


    public EventLocationGQL createEventLocation(EventLocationInput eventLocation) {
        EventLocationService eventLocationService = new EventLocationService();
        return new EventLocationMapperGQL().toModelGQL(
                new EventLocationMapper().toModel(
                        eventLocationService.saveLocation(new EventLocationMapperGQL().toModel(
                                new EventLocationInputMapper().toModelGQL(
                                        eventLocation)))));
    }

    public EventLocationGQL updateEventLocation(String id, EventLocationInput EventLocationInput){
        EventLocationService eventLocationService = new EventLocationService();
        AddressService addressService = new AddressService();
        EventLocationEntity loaded= eventLocationService.getLocationById(Long.parseLong(id)).orElse(null);
        EventLocationGQL eventLocation = new EventLocationMapperGQL().toModelGQL(new EventLocationMapper().toModel(loaded));
        if(EventLocationInput.getName()!=null) eventLocation.setName(EventLocationInput.getName());
        if(EventLocationInput.getCapacity()!=-1) eventLocation.setCapacity(EventLocationInput.getCapacity());
        if(EventLocationInput.getAddress()!=null){
            AddressEntity ae=addressService.getAddressById(Long.parseLong(EventLocationInput.getAddress())).orElse(null);
            eventLocation.setAddress(new AddressMapperGQL().toModelGQL(new AddressMapper().toModel(ae)));
        }
        return new EventLocationMapperGQL().toModelGQL(new EventLocationMapper().toModel(eventLocationService.saveLocation(new EventLocationMapperGQL().toModel(eventLocation))));
    }
    public EventLocationGQL deleteEventLocation(String id){
        EventLocationService eventLocationService = new EventLocationService();
        EventLocationEntity toDelete = eventLocationService.getLocationById(Long.parseLong(id)).orElse(null);
        eventLocationService.deleteLocation(new EventLocationMapper().toModel(toDelete));
        return new EventLocationMapperGQL().toModelGQL(new EventLocationMapper().toModel(toDelete));
    }
}
