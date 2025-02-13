package com.thws.eventmanager.Unit.Infrastructure.GraphQL.resolver.Mapper.MapperGQLDomain;

import com.thws.eventmanager.domain.models.Event;
import com.thws.eventmanager.domain.models.Permission;
import com.thws.eventmanager.domain.models.User;
import com.thws.eventmanager.infrastructure.GraphQL.Models.EventGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Models.PermissionGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Models.UserGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain.EventMapperGQL;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EventMapperGQLTest {
    private final EventMapperGQL eventMapperGQL = new EventMapperGQL();

    @Test
    void testToModel() {
        // Create a sample EventGQL object
        EventGQL eventGQL = new EventGQL();
        eventGQL.setId("1");
        eventGQL.setName("Sample Event");
        eventGQL.setDescription("This is a test event");
        eventGQL.setTicketCount(100);
        eventGQL.setTicketsSold(50);
        eventGQL.setMaxTicketsPerUser(5);

        UserGQL artist1 = new UserGQL();
        artist1.setId("1");
        artist1.setName("Artist 1");
        artist1.setEmail("artist1@gmail.com");
        artist1.setPassword("password");
        artist1.setPermission(PermissionGQL.ARTIST);
        UserGQL artist2 = new UserGQL();
        artist2.setId("2");
        artist2.setName("Artist 2");
        artist2.setEmail("artist2@gmail.com");
        artist2.setPassword("password");
        artist2.setPermission(PermissionGQL.ARTIST);
        UserGQL user = new UserGQL();
        user.setId("3");
        user.setName("Blocked User");
        user.setEmail("user1@gmail.com");
        user.setPassword("password");
        user.setPermission(PermissionGQL.CUSTOMER);

        eventGQL.setArtists(List.of(artist1, artist2));
        eventGQL.setBlockList(List.of(user));
        eventGQL.setTicketPrice(20L);
        eventGQL.setStartDate("2025-02-13T10:00:00");
        eventGQL.setEndDate("2025-02-13T12:00:00");

        // Call the toModel method
        Event event = eventMapperGQL.toModel(eventGQL);

        // Validate the conversion
        assertNotNull(event);
        assertEquals("Sample Event", event.getName());
        assertEquals("This is a test event", event.getDescription());
        assertEquals(100, event.getTicketCount());
        assertEquals(50, event.getTicketsSold());
        assertEquals(5, event.getMaxTicketsPerUser());
        assertEquals(2, event.getArtists().size());
        assertEquals("Artist 1", event.getArtists().getFirst().getName());
        assertEquals("Artist 2", event.getArtists().get(1).getName());
        assertEquals(1, event.getBlockList().size());
        assertEquals("Blocked User", event.getBlockList().getFirst().getName());
        assertEquals(20L, event.getTicketPrice());
        assertEquals("2025-02-13T10:00:00", event.getStartDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        assertEquals("2025-02-13T12:00:00", event.getEndDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }

    @Test
    void testToModelGQL() {
        // Create a sample Event object
        Event event = new Event();
        event.setId(1L);
        event.setName("Sample Event");
        event.setDescription("This is a test event");
        event.setTicketCount(100);
        event.setTicketsSold(50);
        event.setMaxTicketsPerUser(5);

        User artist1 = new User();
        artist1.setId(1L);
        artist1.setName("Artist 1");
        artist1.setEmail("artist1@gmail.com");
        artist1.setPassword("password");
        artist1.setPermission(Permission.ARTIST);
        User artist2 = new User();
        artist2.setId(2L);
        artist2.setName("Artist 2");
        artist2.setEmail("artist2@gmail.com");
        artist2.setPassword("password");
        artist2.setPermission(Permission.ARTIST);
        User user = new User();
        user.setId(3L);
        user.setName("Blocked User");
        user.setEmail("user1@gmail.com");
        user.setPassword("password");
        user.setPermission(Permission.CUSTOMER);

        event.setArtists(List.of(artist1, artist2));
        event.setBlockList(List.of(user));
        event.setTicketPrice(20L);
        event.setStartDate(LocalDateTime.parse("2025-02-13T10:00:00"));
        event.setEndDate(LocalDateTime.parse("2025-02-13T12:00:00"));

        // Call the toModelGQL method
        EventGQL eventGQL = eventMapperGQL.toModelGQL(event);

        // Validate the conversion
        assertNotNull(eventGQL);
        assertEquals("1", eventGQL.getId());
        assertEquals("Sample Event", eventGQL.getName());
        assertEquals("This is a test event", eventGQL.getDescription());
        assertEquals(100, eventGQL.getTicketCount());
        assertEquals(50, eventGQL.getTicketsSold());
        assertEquals(5, eventGQL.getMaxTicketsPerUser());
        assertEquals(2, eventGQL.getArtists().size());
        assertEquals("Artist 1", eventGQL.getArtists().getFirst().getName());
        assertEquals("Artist 2", eventGQL.getArtists().get(1).getName());
        assertEquals(1, eventGQL.getBlockList().size());
        assertEquals("Blocked User", eventGQL.getBlockList().getFirst().getName());
        assertEquals(20L, eventGQL.getTicketPrice());
        assertEquals("2025-02-13T10:00:00", eventGQL.getStartDate());
        assertEquals("2025-02-13T12:00:00", eventGQL.getEndDate());
    }

    @Test
    void testToModel_withNullInput() {
        // Test with null EventGQL
        Event event = eventMapperGQL.toModel(null);
        assertNull(event);
    }

    @Test
    void testToModelGQL_withNullInput() {
        // Test with null Event
        EventGQL eventGQL = eventMapperGQL.toModelGQL(null);
        assertNull(eventGQL);
    }
}