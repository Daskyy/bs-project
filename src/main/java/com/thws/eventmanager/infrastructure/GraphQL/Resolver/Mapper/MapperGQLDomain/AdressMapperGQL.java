package com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain;

import com.thws.eventmanager.domain.models.Address;
import com.thws.eventmanager.infrastructure.GraphQL.Models.AddressGQL;

public class AdressMapperGQL extends Mapper<Address, AddressGQL> {

    @Override
    public Address toModel(AddressGQL addressgql){
        if (addressgql == null) return null;
        Address address = new Address();
        address.setStreet(addressgql.getStreet());
        address.setNo(addressgql.getNo());
        address.setCity(addressgql.getCity());
        address.setZipCode(addressgql.getZipCode());
        address.setCountry(addressgql.getCountry());
        // id wird beim Erstellen in DB vergeben
        return address;
    }

    @Override
    public AddressGQL toModelGQL(Address address){
        if (address == null) return null;
        AddressGQL addressgql = new AddressGQL();
        addressgql.setStreet(address.getStreet());
        addressgql.setNo(address.getNo());
        addressgql.setCity(address.getCity());
        addressgql.setZipCode(address.getZipCode());
        addressgql.setCountry(address.getCountry());
        addressgql.setId(String.valueOf(address.getId()));
        return addressgql;
    }
}
