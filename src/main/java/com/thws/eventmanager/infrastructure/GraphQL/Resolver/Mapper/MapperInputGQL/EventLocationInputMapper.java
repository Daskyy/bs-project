package com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperInputGQL;

import com.thws.eventmanager.domain.services.models.AddressService;
import com.thws.eventmanager.infrastructure.GraphQL.InputModels.EventInput;
import com.thws.eventmanager.infrastructure.GraphQL.InputModels.EventLocationInput;
import com.thws.eventmanager.infrastructure.GraphQL.Models.AddressGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Models.EventLocationGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain.AddressMapperGQL;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.AddressMapper;


public class EventLocationInputMapper {

    public  EventLocationGQL toModelGQL(EventLocationInput input) {
        AddressInputMapper addressInputMapper = new AddressInputMapper();
        if(input == null) return null;
        EventLocationGQL gql = new EventLocationGQL();
        gql.setAddress(new AddressMapperGQL().toModelGQL(new AddressMapper().toModel(new AddressService().getAddressById(Long.parseLong(input.getAddress())).orElse(null))));
        gql.setName(input.getName());
        gql.setCapacity(input.getCapacity());
        gql.setId(input.getId());
        return gql;
    }
}
