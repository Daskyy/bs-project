package com.thws.eventmanager.Unit.Infrastructure.persistence.mapper;

import com.thws.eventmanager.domain.models.Address;
import com.thws.eventmanager.infrastructure.components.persistence.entities.AddressEntity;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.AddressMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressMapperTest {
    @Test
    void toModel_ShouldMapCorrectly() {
        AddressEntity entity = new AddressEntity();
        entity.setCity("Berlin");
        entity.setCountry("Germany");
        entity.setNo(42);
        entity.setZipCode(10115);
        entity.setStreet("Main Street");
        entity.setId(1);

        Address address = new AddressMapper().toModel(entity);

        assertEquals("Berlin", address.getCity());
        assertEquals("Germany", address.getCountry());
        assertEquals(42, address.getNo());
        assertEquals(10115, address.getZipCode());
        assertEquals("Main Street", address.getStreet());
        assertEquals(1, address.getId());
    }

    @Test
    void toEntityTest(){
        Address model = new Address();
        model.setCity("Paris");
        model.setCountry("France");
        model.setNo(23);
        model.setZipCode(75001);
        model.setStreet("Rue de Rivoli");

        AddressEntity entity = new AddressMapper().toEntity(model);

        assertEquals("Paris", entity.getCity());
        assertEquals("France", entity.getCountry());
        assertEquals(23, entity.getNo());
        assertEquals(75001, entity.getZipCode());
        assertEquals("Rue de Rivoli", entity.getStreet());

    }
}