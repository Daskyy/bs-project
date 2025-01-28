package com.thws.eventmanager.infrastructure.components.persistence.mapper;

import com.thws.eventmanager.domain.models.Address;
import com.thws.eventmanager.infrastructure.components.persistence.entities.AddressEntity;

public class AddressMapper extends Mapper<Address, AddressEntity> {
    @Override
    public Address toModel(AddressEntity entity){
        Address address = new Address();

        address.setCity(entity.getCity());
        address.setCountry(entity.getCountry());
        address.setNo(entity.getNo());
        address.setZipCode(entity.getZipCode());
        address.setStreet(entity.getStreet());

        return address;
    }
    @Override
    public AddressEntity toEntity(Address model){
        AddressEntity entity = new AddressEntity();

        entity.setCity(model.getCity());
        entity.setCountry(model.getCountry());
        entity.setNo(model.getNo());
        entity.setZipCode(model.getZipCode());
        entity.setStreet(model.getStreet());

        return entity;
    }
}
