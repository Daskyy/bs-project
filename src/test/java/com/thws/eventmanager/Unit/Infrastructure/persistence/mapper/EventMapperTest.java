package com.thws.eventmanager.Unit.Infrastructure.persistence.mapper;

import com.thws.eventmanager.domain.models.*;
import com.thws.eventmanager.infrastructure.components.persistence.entities.AddressEntity;
import com.thws.eventmanager.infrastructure.components.persistence.entities.EventEntity;
import com.thws.eventmanager.infrastructure.components.persistence.entities.EventLocationEntity;
import com.thws.eventmanager.infrastructure.components.persistence.entities.UserEntity;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.EventMapper;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventMapperTest {
    private final EventMapper mapper = new EventMapper();

    @Test
    void toModelTest() {
        UserEntity userEntity1 = new UserEntity();
        userEntity1.setId(1L);

        UserEntity userEntity2 = new UserEntity();
        userEntity2.setId(2L);

        UserEntity userEntity3 = new UserEntity();
        userEntity3.setId(3L);

        //location with empty address for correct functioning
        EventLocationEntity locationEntity = new EventLocationEntity();
        locationEntity.setName("Sample Venue");
        locationEntity.setAddress(new AddressEntity());
        locationEntity.setId(2L);

        //fill all attributes to test the mapper
        EventEntity eventEntity = new EventEntity();
        eventEntity.setId(1L);
        eventEntity.setName("Sample Event");
        eventEntity.setDescription("This is a sample event");
        eventEntity.setTicketCount(1000);
        eventEntity.setTicketsSold(500);
        eventEntity.setMaxTicketsPerUser(5);
        eventEntity.setStartDate(LocalDateTime.of(2025, 12, 15, 10, 10));
        eventEntity.setEndDate(LocalDateTime.of(2025, 12, 15, 10, 15));
        eventEntity.setArtists(List.of(userEntity1, userEntity2));
        eventEntity.setBlockList(List.of(userEntity3));
        eventEntity.setLocation(locationEntity);

        Event event = mapper.toModel(eventEntity);

        assertEquals("Sample Event", event.getName());
        assertEquals("This is a sample event", event.getDescription());
        assertEquals(1000, event.getTicketCount());
        assertEquals(500, event.getTicketsSold());
        assertEquals(5, event.getMaxTicketsPerUser());
        assertEquals(LocalDateTime.of(2025, 12, 15, 10, 10), event.getStartDate());
        assertEquals(LocalDateTime.of(2025, 12, 15, 10, 15), event.getEndDate());
        assertEquals(2, event.getArtists().size());
        assertEquals(1, event.getBlockList().size());
        assertEquals("Sample Venue", event.getLocation().getName());
        assertEquals(1L, event.getId());

    }

    @Test
    void toEntityTest() {
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();

        EventLocation location = new EventLocation();
        location.setName("Sample Venue");
        location.setAddress(new Address());

        //fill attributes to test the mapper
        Event event = new Event();
        event.setName("Sample Event");
        event.setDescription("This is a sample event");
        event.setTicketCount(1000);
        event.setTicketsSold(500);
        event.setMaxTicketsPerUser(5);
        event.setStartDate(LocalDateTime.of(2025, 12, 15, 10, 10));
        event.setEndDate(LocalDateTime.of(2025, 12, 15, 10, 15));
        event.setArtists(List.of(user1, user2));
        event.setBlockList(List.of(user3));
        event.setLocation(location);

        EventEntity eventEntity = mapper.toEntity(event);

        assertEquals("Sample Event", eventEntity.getName());
        assertEquals("This is a sample event", eventEntity.getDescription());
        assertEquals(1000, eventEntity.getTicketCount());
        assertEquals(500, eventEntity.getTicketsSold());
        assertEquals(5, eventEntity.getMaxTicketsPerUser());
        assertEquals(LocalDateTime.of(2025, 12, 15, 10, 10), eventEntity.getStartDate());
        assertEquals(LocalDateTime.of(2025, 12, 15, 10, 15), eventEntity.getEndDate());
        assertEquals(2, eventEntity.getArtists().size());
        assertEquals(1, eventEntity.getBlockList().size());
        assertEquals("Sample Venue", eventEntity.getLocation().getName());
    }
}
