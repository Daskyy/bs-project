package com.thws.eventmanager.Unit.Infrastructure.GraphQL.resolver.Mapper.MapperGQLDomain;

import com.thws.eventmanager.domain.models.Address;
import com.thws.eventmanager.domain.models.EventLocation;
import com.thws.eventmanager.infrastructure.GraphQL.Models.AddressGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Models.EventLocationGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain.EventLocationMapperGQL;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventLocationMapperGQLTest {
    private final EventLocationMapperGQL mapper = new EventLocationMapperGQL();

    @Test
    void toModelTest(){
        EventLocationGQL eventLocationGQL = new EventLocationGQL();
        eventLocationGQL.setId("123");
        AddressGQL addressGQL = new AddressGQL();
        addressGQL.setStreet("Main St");
        addressGQL.setNo(101);
        addressGQL.setCity("Springfield");
        addressGQL.setZipCode(12345);
        addressGQL.setCountry("USA");
        eventLocationGQL.setAddress(addressGQL);
        eventLocationGQL.setName("Concert Hall");
        eventLocationGQL.setCapacity(5000);

        EventLocation eventLocation = mapper.toModel(eventLocationGQL);

        assertNotNull(eventLocation);
        assertNotNull(eventLocation.getAddress());
        assertEquals("Main St", eventLocation.getAddress().getStreet());
        assertEquals(101, eventLocation.getAddress().getNo());
        assertEquals("Springfield", eventLocation.getAddress().getCity());
        assertEquals(12345, eventLocation.getAddress().getZipCode());
        assertEquals("USA", eventLocation.getAddress().getCountry());
        assertEquals("Concert Hall", eventLocation.getName());
        assertEquals(5000, eventLocation.getCapacity());
    }
    @Test
    void testToModelNull() {
        EventLocation eventLocation = mapper.toModel(null);
        assertNull(eventLocation);
    }
    @Test
    void toModelGQLTest(){
        EventLocation eventLocation = new EventLocation();
        eventLocation.setId(123L);
        Address address = new Address();
        address.setStreet("Main St");
        address.setNo(101);
        address.setCity("Springfield");
        address.setZipCode(12345);
        address.setCountry("USA");
        eventLocation.setAddress(address);
        eventLocation.setName("Concert Hall");
        eventLocation.setCapacity(5000);

        EventLocationGQL eventLocationGQL = mapper.toModelGQL(eventLocation);

        assertNotNull(eventLocationGQL);
        assertEquals("123", eventLocationGQL.getId());
        assertNotNull(eventLocationGQL.getAddress());
        assertEquals("Main St", eventLocationGQL.getAddress().getStreet());
        assertEquals(101, eventLocationGQL.getAddress().getNo());
        assertEquals("Springfield", eventLocationGQL.getAddress().getCity());
        assertEquals(12345, eventLocationGQL.getAddress().getZipCode());
        assertEquals("USA", eventLocationGQL.getAddress().getCountry());
        assertEquals("Concert Hall", eventLocationGQL.getName());
        assertEquals(5000, eventLocationGQL.getCapacity());
    }

    @Test
    void testToModelGQLNull() {
        EventLocationGQL eventLocationGQL = mapper.toModelGQL(null);
        assertNull(eventLocationGQL);
    }
}