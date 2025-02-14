package com.thws.eventmanager.domain.port.in;

import com.thws.eventmanager.domain.models.Address;
import com.thws.eventmanager.infrastructure.components.persistence.entities.AddressEntity;

import java.util.List;
import java.util.Optional;

public interface AddressServiceInterface {
    public AddressEntity saveAddress(Address address);
    public Optional<AddressEntity> deleteAddress(Address address);
    public Optional<AddressEntity> getAddressById(long id);
    public List<AddressEntity> getAllAddresses(List<String> criteria, List<Object> values, Integer page, Integer pageSize);
    public List<AddressEntity> getAllAddresses();
}
