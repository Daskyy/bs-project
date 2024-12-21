package com.thws.eventmanager.application.port.out;

import com.thws.eventmanager.domain.models.Address;

import java.util.List;

public interface AddressRepository {
    void saveAddress(Address address);
    List<Address> getAllAddresses();
}
