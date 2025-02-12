package com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperInputGQL;

import com.thws.eventmanager.infrastructure.GraphQL.InputModels.EventInput;
import com.thws.eventmanager.infrastructure.GraphQL.InputModels.EventLocationInput;
import com.thws.eventmanager.infrastructure.GraphQL.Models.AddressGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Models.EventLocationGQL;


public class EventLocationInputMapper {

    public  EventLocationGQL toModelGQL(EventLocationInput input) {
        AddressInputMapper addressInputMapper = new AddressInputMapper();
        if(input == null) return null;
        EventLocationGQL gql = new EventLocationGQL();
        gql.setAddress(addressInputMapper.toModelGQL(input.getAddress()));
        gql.setName(input.getName());
        gql.setCapacity(input.getCapacity());

        return gql;
    }
}
