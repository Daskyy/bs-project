package com.thws.eventmanager.Unit.Infrastructure.persistence.mapper;

import com.thws.eventmanager.domain.models.Address;
import com.thws.eventmanager.domain.models.EventLocation;
import com.thws.eventmanager.infrastructure.components.persistence.entities.AddressEntity;
import com.thws.eventmanager.infrastructure.components.persistence.entities.EventLocationEntity;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.EventLocationMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventLocationMapperTest {
    private final EventLocationMapper mapper = new EventLocationMapper();

    @Test
    void toModelTest() {
        //fill all attributes to test the full mapper

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setStreet("Main St");
        addressEntity.setCity("Sample City");
        addressEntity.setCountry("Sample Country");
        addressEntity.setNo(123);
        addressEntity.setZipCode(10001);

        EventLocationEntity eventLocationEntity = new EventLocationEntity();
        eventLocationEntity.setId(1L);
        eventLocationEntity.setName("Sample Venue");
        eventLocationEntity.setCapacity(500);
        eventLocationEntity.setAddress(addressEntity);

        EventLocation eventLocation = mapper.toModel(eventLocationEntity);

        assertEquals(1L, eventLocation.getId());
        assertEquals("Sample Venue", eventLocation.getName());
        assertEquals(500, eventLocation.getCapacity());
        assertNotNull(eventLocation.getAddress());
        assertEquals("Main St", eventLocation.getAddress().getStreet());
        assertEquals("Sample City", eventLocation.getAddress().getCity());
        assertEquals("Sample Country", eventLocation.getAddress().getCountry());
        assertEquals(123, eventLocation.getAddress().getNo());
        assertEquals(10001, eventLocation.getAddress().getZipCode());
    }
    @Test
    void toEntityTest() {
        Address address = new Address("Sample City", "Sample Country", 123, "Main St", 10001);
        EventLocation eventLocation = new EventLocation(address, 500, "Sample Venue");
        eventLocation.setId(1L);
        eventLocation.setAddress(address);

        EventLocationEntity eventLocationEntity = mapper.toEntity(eventLocation);

        assertEquals(1L, eventLocationEntity.getId());
        assertEquals("Sample Venue", eventLocationEntity.getName());
        assertEquals(500, eventLocationEntity.getCapacity());
        assertNotNull(eventLocationEntity.getAddress());
        assertEquals("Sample City", eventLocationEntity.getAddress().getCity());
        assertEquals("Sample Country", eventLocationEntity.getAddress().getCountry());
        assertEquals(123, eventLocationEntity.getAddress().getNo());
        assertEquals(10001, eventLocationEntity.getAddress().getZipCode());
    }
}