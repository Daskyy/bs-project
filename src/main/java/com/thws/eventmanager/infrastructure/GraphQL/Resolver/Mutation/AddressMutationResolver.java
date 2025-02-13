package com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mutation;

import com.thws.eventmanager.domain.models.Address;
import com.thws.eventmanager.domain.services.models.AddressService;
import com.thws.eventmanager.infrastructure.GraphQL.InputModels.AddressInput;
import com.thws.eventmanager.infrastructure.GraphQL.Models.AddressGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain.AdressMapperGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperInputGQL.AddressInputMapper;
import com.thws.eventmanager.infrastructure.components.persistence.entities.AddressEntity;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.AddressMapper;
import graphql.kickstart.tools.GraphQLMutationResolver;

import java.util.Objects;

public class AddressMutationResolver implements GraphQLMutationResolver {
    AddressMapper addressMapper = new AddressMapper();
    AdressMapperGQL adressMapperGQL = new AdressMapperGQL();
    AddressInputMapper addressInputMapper = new AddressInputMapper();

    public AddressGQL createAddress(AddressInput input){
        Address address = adressMapperGQL.toModel(addressInputMapper.toModelGQL(input));
        AddressService addressService = new AddressService();
        return adressMapperGQL.toModelGQL(addressMapper.toModel(addressService.saveAddress(address)));
    }

    public AddressGQL deleteAddress(String id){
        AddressService addressService = new AddressService();
        AddressEntity toDelete = addressService.getAddressById(Long.parseLong(id)).orElse(null);
        Address address = addressMapper.toModel(Objects.requireNonNull(toDelete));
        return adressMapperGQL.toModelGQL(addressMapper.toModel(Objects.requireNonNull(addressService.deleteAddress(address).orElse(null))));
    }
}
