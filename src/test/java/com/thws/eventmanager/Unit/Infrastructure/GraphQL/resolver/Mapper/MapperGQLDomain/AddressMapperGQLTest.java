package com.thws.eventmanager.Unit.Infrastructure.GraphQL.resolver.Mapper.MapperGQLDomain;

import com.thws.eventmanager.domain.models.Address;
import com.thws.eventmanager.infrastructure.GraphQL.Models.AddressGQL;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.AddressMapper;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddressMapperGQLTest {
    private final AdressMapperGQL mapper = new AdressMapperGQL();

    @Test
    void toModelTest() {
        AddressGQL addressGQL = new AddressGQL();
        addressGQL.setId("123");
        addressGQL.setStreet("Main St");
        addressGQL.setNo(101);
        addressGQL.setCity("Springfield");
        addressGQL.setZipCode(12345);
        addressGQL.setCountry("USA");

        Address address = mapper.toModel(addressGQL);

        assertNotNull(address);
        assertEquals(101, address.getNo());
        assertEquals("Main St", address.getStreet());
        assertEquals("Springfield", address.getCity());
        assertEquals(12345, address.getZipCode());
        assertEquals("USA", address.getCountry());
    }

    @Test
    void toModelGQLTest() {
        Address address = new Address();
        address.setId(123);
        address.setStreet("Main St");
        address.setNo(101);
        address.setCity("Springfield");
        address.setZipCode(12345);
        address.setCountry("USA");

        AddressGQL addressGQL = mapper.toModelGQL(address);

        assertNotNull(addressGQL);
        assertEquals("123", addressGQL.getId());
        assertEquals("Main St", addressGQL.getStreet());
        assertEquals(101, addressGQL.getNo());
        assertEquals("Springfield", addressGQL.getCity());
        assertEquals(12345, addressGQL.getZipCode());
        assertEquals("USA", addressGQL.getCountry());
    }

    @Test
    void toModelNull() {
        Address address = mapper.toModel(null);
        assertNull(address);
    }

    @Test
    void toModelGQLNull() {
        AddressGQL addressGQL = mapper.toModelGQL(null);
        assertNull(addressGQL);
    }
}
