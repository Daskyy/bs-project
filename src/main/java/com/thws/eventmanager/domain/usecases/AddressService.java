package com.thws.eventmanager.domain.usecases;

import com.thws.eventmanager.domain.exceptions.InvalidEventException;
import com.thws.eventmanager.domain.models.Address;
import com.thws.eventmanager.domain.port.in.AddressServiceInterface;
import com.thws.eventmanager.infrastructure.components.persistence.adapter.AddressHandler;
import com.thws.eventmanager.infrastructure.components.persistence.entities.AddressEntity;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.AddressMapper;
import org.hibernate.sql.model.jdbc.OptionalTableUpdateOperation;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AddressService implements AddressServiceInterface {
    AddressMapper addressMapper = new AddressMapper();
    @Override
    public AddressEntity saveAddress(Address address) {
        try (AddressHandler addressHandler = new AddressHandler()) {
            return addressHandler.save(addressMapper.toEntity(address));
        } catch (Exception e) {
            throw new InvalidEventException("Failed to save address to database.");
        }
    }

    @Override
    public Optional<AddressEntity> deleteAddress(Address address) {
        try (AddressHandler addressHandler = new AddressHandler()) {
            return addressHandler.deleteById(address.getId());
        } catch (Exception e) {
            throw new InvalidEventException("Failed to delete address from database.");
        }
    }

    @Override
    public Optional<AddressEntity> getAddressById(long id) {
        try (AddressHandler addressHandler = new AddressHandler()) {
            return addressHandler.findById(id);
        } catch (Exception e) {
            throw new InvalidEventException("Failed to get address from database.");
        }
    }

    @Override
    public List<AddressEntity> getAllAddresses(List<String> criteria, List<Object> values) {
        System.out.println("Criteria: " + criteria);
        System.out.println("Values: " + values);

        try (AddressHandler addressHandler = new AddressHandler()) {
            if (criteria.size() != values.size()) {
                throw new InvalidEventException("Criteria and values lists must have the same size.");
            } else if (criteria.isEmpty()) {
                return addressHandler.findAll();
            } else {
                return addressHandler.searchByCriteria(criteria, values);
            }
        } catch (Exception e) {
            throw new InvalidEventException("Failed to get filtered addresses from database.");
        }
    }

    @Override
    public List<AddressEntity> getAllAddresses() {
        try (AddressHandler addressHandler = new AddressHandler()) {
            return addressHandler.findAll();
        } catch (Exception e) {
            throw new InvalidEventException("Failed to get all addresses from database.");
        }
    }
}
