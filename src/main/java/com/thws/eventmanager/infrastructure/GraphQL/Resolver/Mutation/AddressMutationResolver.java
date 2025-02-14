package com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mutation;

import com.thws.eventmanager.domain.models.Address;
import com.thws.eventmanager.domain.services.models.AddressService;
import com.thws.eventmanager.infrastructure.GraphQL.InputModels.AddressInput;
import com.thws.eventmanager.infrastructure.GraphQL.Models.AddressGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain.AddressMapperGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperInputGQL.AddressInputMapper;
import com.thws.eventmanager.infrastructure.components.persistence.entities.AddressEntity;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.AddressMapper;
import graphql.kickstart.tools.GraphQLMutationResolver;

import java.util.Objects;

public class AddressMutationResolver implements GraphQLMutationResolver {
    AddressMapper addressMapper = new AddressMapper();
    AddressMapperGQL addressMapperGQL = new AddressMapperGQL();
    AddressInputMapper addressInputMapper = new AddressInputMapper();

    public AddressGQL createAddress(AddressInput input){
        Address address = addressMapperGQL.toModel(addressInputMapper.toModelGQL(input));
        AddressService addressService = new AddressService();
        return addressMapperGQL.toModelGQL(addressMapper.toModel(addressService.saveAddress(address)));
    }

    public AddressGQL deleteAddress(String id){
        AddressService addressService = new AddressService();
        AddressEntity toDelete = addressService.getAddressById(Long.parseLong(id)).orElse(null);
        Address address = addressMapper.toModel(Objects.requireNonNull(toDelete));
        return addressMapperGQL.toModelGQL(addressMapper.toModel(Objects.requireNonNull(addressService.deleteAddress(address).orElse(null))));
    }
    public AddressGQL updateAddress(String id, AddressInput input){
        AddressService addressService = new AddressService();
        AddressEntity loaded = addressService.getAddressById(Long.parseLong(id)).orElse(null);
        Address address = addressMapper.toModel(Objects.requireNonNull(loaded));
        if(input.getStreet()!=null) address.setStreet(input.getStreet());
        if(input.getCity()!=null) address.setCity(input.getCity());
        if(input.getZipCode()!=-1) address.setZipCode(input.getZipCode());
        if(input.getCountry()!=null) address.setCountry(input.getCountry());
        if(input.getNo()!=-1) address.setNo(input.getNo());
        return addressMapperGQL.toModelGQL(addressMapper.toModel(Objects.requireNonNull(addressService.saveAddress(address))));
    }
}
