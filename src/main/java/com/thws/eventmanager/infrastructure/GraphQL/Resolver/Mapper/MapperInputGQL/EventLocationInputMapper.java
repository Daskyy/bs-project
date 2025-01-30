package com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperInputGQL;

import com.thws.eventmanager.infrastructure.GraphQL.InputModels.EventInput;
import com.thws.eventmanager.infrastructure.GraphQL.InputModels.EventLocationInput;
import com.thws.eventmanager.infrastructure.GraphQL.Models.AddressGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Models.EventLocationGQL;


public class EventLocationInputMapper {

    public static  EventLocationGQL toModelGQL(EventInput eventInput) {
        EventLocationInput eLI = eventInput.getLocation();
        EventLocationGQL eLGQL = new EventLocationGQL();
        eLGQL.setName(eLI.getName());
        eLGQL.setCapacity(eLI.getCapacity());
        AddressGQL addressGQL = new AddressGQL();
        addressGQL.setStreet(eLI.getAddress().getStreet());
        addressGQL.setNo(eLI.getAddress().getNo());
        addressGQL.setCity(eLI.getAddress().getCity());
        addressGQL.setZipCode(eLI.getAddress().getZipCode());
        addressGQL.setCountry(eLI.getAddress().getCountry());


        eLGQL.setAddress(addressGQL);
        return eLGQL;
    }
}
