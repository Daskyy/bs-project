package com.thws.eventmanager.database;

import com.github.javafaker.Faker;
import com.thws.eventmanager.domain.models.*;
import com.thws.eventmanager.domain.usecases.EventService;
import com.thws.eventmanager.infrastructure.components.persistence.adapter.EventHandler;
import com.thws.eventmanager.infrastructure.components.persistence.adapter.UserHandler;
import com.thws.eventmanager.infrastructure.components.persistence.entities.UserEntity;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.UserMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CreateEvent {
    public static void main(String[] args) {
        try(EventHandler eventHandler = new EventHandler()) {
            Faker faker = new Faker();
            Event event = new Event();
            EventLocation eventLocation = new EventLocation();
            Address address = new Address();
            User user = new User();

            // USER
            user.setName(faker.name().fullName());
            user.setEmail(faker.internet().emailAddress());
            user.setPassword(faker.internet().password());
            user.setPermission(Permission.ARTIST);

            // ADDRESS
            address.setStreet(faker.address().streetName());
            address.setZipCode(12345);
            address.setNo(1);
            address.setCity(faker.address().city());
            address.setCountry(faker.address().country());

            // EVENT LOCATION
            eventLocation.setAddress(address);
            eventLocation.setCapacity(1000);
            eventLocation.setName(faker.company().name());

            // EVENT
            event.setName(faker.company().name());
            LocalDateTime startDate = LocalDateTime.now().plusYears(1);
            LocalDateTime endDate = startDate.plusHours(2);
            event.setStartDate(startDate);
            event.setEndDate(endDate);
            event.setBlockList(new ArrayList<User>());
            event.setMaxTicketsPerUser(faker.number().numberBetween(1, 10));
            event.setTicketCount(faker.number().numberBetween(10, eventLocation.getCapacity()));
            event.setTicketsSold(faker.number().numberBetween(0, (int) event.getTicketCount()));
            event.setArtists(List.of(user)); // Set the user in the artists list
            event.setLocation(eventLocation);
            event.setDescription(faker.lorem().paragraph());
            event.setTicketPrice(140);

            EventService eventService = new EventService(eventHandler);
            eventService.createEvent(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
