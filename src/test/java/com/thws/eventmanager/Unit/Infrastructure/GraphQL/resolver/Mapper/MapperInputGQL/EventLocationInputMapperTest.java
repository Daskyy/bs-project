package com.thws.eventmanager.Unit.Infrastructure.GraphQL.resolver.Mapper.MapperInputGQL;

import com.thws.eventmanager.infrastructure.GraphQL.InputModels.AddressInput;
import com.thws.eventmanager.infrastructure.GraphQL.InputModels.EventInput;
import com.thws.eventmanager.infrastructure.GraphQL.InputModels.EventLocationInput;
import com.thws.eventmanager.infrastructure.GraphQL.Models.EventLocationGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperInputGQL.EventLocationInputMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EventLocationInputMapperTest {
    private final EventLocationInputMapper mapper = new EventLocationInputMapper();

    @Test
    void testToModelGQL() {
        AddressInput addressInput = new AddressInput();
        addressInput.setStreet("Main St");
        addressInput.setNo(101);
        addressInput.setCity("Springfield");
        addressInput.setZipCode(12345);
        addressInput.setCountry("USA");

        EventLocationInput eventLocationInput = new EventLocationInput();
        eventLocationInput.setName("Concert Hall");
        eventLocationInput.setCapacity(5000);
        eventLocationInput.setAddress(addressInput);

        EventInput eventInput = new EventInput();
        eventInput.setLocation(eventLocationInput);

        EventLocationGQL eventLocationGQL = mapper.toModelGQL(eventInput);

        assertNotNull(eventLocationGQL);
        assertEquals("Concert Hall", eventLocationGQL.getName());
        assertEquals(5000, eventLocationGQL.getCapacity());

        assertNotNull(eventLocationGQL.getAddress());
        assertEquals("Main St", eventLocationGQL.getAddress().getStreet());
        assertEquals(101, eventLocationGQL.getAddress().getNo());
        assertEquals("Springfield", eventLocationGQL.getAddress().getCity());
        assertEquals(12345, eventLocationGQL.getAddress().getZipCode());
        assertEquals("USA", eventLocationGQL.getAddress().getCountry());
    }
}
