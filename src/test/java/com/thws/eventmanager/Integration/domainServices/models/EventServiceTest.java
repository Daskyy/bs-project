package com.thws.eventmanager.Integration.domainServices.models;

import com.github.javafaker.Faker;
import com.thws.eventmanager.domain.models.*;
import com.thws.eventmanager.domain.services.models.EventService;
import com.thws.eventmanager.domain.services.models.UserService;
import com.thws.eventmanager.infrastructure.components.persistence.entities.EventEntity;
import com.thws.eventmanager.infrastructure.components.persistence.entities.UserEntity;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.EventMapper;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.UserMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EventServiceTest {
    private final EventService eventService = new EventService();
    private final EventMapper EventMapper = new EventMapper();
    private final UserMapper userMapper = new UserMapper();
    private final UserService userService = new UserService();
    private final Faker faker = new Faker();
    private User userToBlock;

    private Event generateRandomEvent() {
        Event event = new Event();
        User user = new User();
        user.setName(faker.name().firstName());
        user.setEmail(faker.internet().emailAddress());
        user.setPassword(faker.internet().password());
        user.setPermission(Permission.CUSTOMER);

        User artist = new User();
        artist.setName(faker.name().firstName());
        artist.setEmail(faker.internet().emailAddress());
        artist.setPassword(faker.internet().password());
        artist.setPermission(Permission.ARTIST);

        Address address = new Address();
        address.setStreet(faker.address().streetAddress());
        address.setCity(faker.address().city());
        address.setZipCode(faker.number().numberBetween(0, 99999));
        address.setCountry(faker.address().country());
        address.setNo(faker.number().numberBetween(0, 1000));

        EventLocation eventLocation = new EventLocation();
        eventLocation.setName(faker.company().name());
        eventLocation.setCapacity(faker.number().numberBetween(0, 10000));
        eventLocation.setAddress(address);
        event.setLocation(eventLocation);

        event.setName(faker.book().title());
        event.setDescription(faker.lorem().sentence());
        event.setStartDate(LocalDateTime.now().plusDays(1));
        event.setEndDate(LocalDateTime.now().plusDays(2));
        event.setTicketCount(faker.number().numberBetween(50, 100));
        event.setTicketsSold(faker.number().numberBetween(1, 50));
        event.setMaxTicketsPerUser(10);
        event.setArtists(new ArrayList<>(List.of(artist)));
        event.setBlockList(new ArrayList<>());
        event.setLocation(eventLocation);
        event.setTicketPrice(faker.number().numberBetween(1, 500));

        //persist user
        this.userToBlock = user;
        UserEntity savedUser = userService.createUser(userToBlock);
        userToBlock.setId(savedUser.getId());
        return event;
    }

    @Test
    public void testCreateandGetEventById() {
        Event event = generateRandomEvent();
        EventEntity savedEvent = assertDoesNotThrow(() -> eventService.createEvent(event));
        long eventId = savedEvent.getId();
        Optional<EventEntity> retrievedEvent = eventService.getEventById(eventId);

        assertNotNull(savedEvent);
        assertTrue(retrievedEvent.isPresent());
        assertEquals(savedEvent.getId(), retrievedEvent.get().getId());
    }

    @Test
    public void testDeleteEvent() {
        Event event = generateRandomEvent();
        EventEntity savedEvent = eventService.createEvent(event);
        event.setId(savedEvent.getId());
        Optional<EventEntity> deletedEvent = assertDoesNotThrow(() -> eventService.deleteEvent(event));
        assertTrue(deletedEvent.isPresent());
        assertEquals(savedEvent.getId(), deletedEvent.get().getId());
    }

    @Test
    void testBlockUser() {
        Event event = generateRandomEvent();
        EventEntity savedEvent = eventService.createEvent(event);
        event.setId(savedEvent.getId());
        EventEntity updatedEvent = eventService.blockUser(event, userToBlock);
        long blockId = updatedEvent.getBlockList().get(0).getId();
        UserEntity blocked = userMapper.toEntity(userToBlock);
        blocked.setId(blockId);
        assertEquals(blocked.getId(), updatedEvent.getBlockList().getFirst().getId());
    }

    @Test
    void testRefundEvent() {
        Event event = generateRandomEvent();
        EventEntity savedEvent = eventService.createEvent(event);
        event.setId(savedEvent.getId());
        boolean refunded = assertDoesNotThrow(() -> eventService.refundEvent(event));
        assertTrue(refunded);
    }

    @Test
    void testGetAllEventsWithFiltersAndPagination() {
        Event event1 = generateRandomEvent();
        EventEntity savedEvent1 = eventService.createEvent(event1);
        event1.setId(savedEvent1.getId());

        Event event2 = generateRandomEvent();
        EventEntity savedEvent2 = eventService.createEvent(event2);
        event2.setId(savedEvent2.getId());

        List<EventEntity> events = assertDoesNotThrow(() ->
                eventService.getAllEvents(List.of("name"), List.of(event1.getName()), 1, 10));

        assertFalse(events.isEmpty());
        assertEquals(savedEvent1.getName(), events.getFirst().getName());
    }
}