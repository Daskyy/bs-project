package com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperInputGQL;

import com.thws.eventmanager.infrastructure.GraphQL.InputModels.AddressInput;
import com.thws.eventmanager.infrastructure.GraphQL.Models.AddressGQL;

public class AddressInputMapper {

    public AddressGQL toModelGQL(AddressInput input) {
        if(input == null) return null;
        AddressGQL gql = new AddressGQL();
        gql.setStreet(input.getStreet());
        gql.setNo(input.getNo());
        gql.setCity(input.getCity());
        gql.setZipCode(input.getZipCode());
        gql.setCountry(input.getCountry());
        return gql;
    }

}
