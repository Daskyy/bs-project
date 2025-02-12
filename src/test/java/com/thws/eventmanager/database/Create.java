/*
package com.thws.eventmanager.database;

import com.github.javafaker.Faker;
import com.thws.eventmanager.domain.models.Address;
import com.thws.eventmanager.domain.models.Event;
import com.thws.eventmanager.domain.models.EventLocation;
import com.thws.eventmanager.domain.models.User;
import com.thws.eventmanager.domain.usecases.EventService;
import com.thws.eventmanager.infrastructure.components.persistence.PersistenceManager;
import com.thws.eventmanager.infrastructure.components.persistence.adapter.AddressHandler;
import com.thws.eventmanager.infrastructure.components.persistence.adapter.EventHandler;
import com.thws.eventmanager.infrastructure.components.persistence.adapter.EventLocationHandler;
import com.thws.eventmanager.infrastructure.components.persistence.adapter.UserHandler;
import com.thws.eventmanager.infrastructure.components.persistence.entities.AddressEntity;
import com.thws.eventmanager.infrastructure.components.persistence.entities.EventEntity;
import com.thws.eventmanager.infrastructure.components.persistence.entities.EventLocationEntity;
import com.thws.eventmanager.infrastructure.components.persistence.entities.UserEntity;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.AddressMapper;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.EventLocationMapper;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.EventMapper;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.UserMapper;
import jakarta.persistence.EntityManager;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.TimeUnit;

// creating adresses and event locations works, but creating events gives me stupid errors
// edit: seems to be working now
// TODO: fix this trashy mess


public class Create {
    public static void main(String[] args) {
        try(PersistenceManager persistenceManager = PersistenceManager.create()) {
            Faker faker = new Faker();
            EntityManager entityManager = persistenceManager.getEntityManager();

            AddressHandler addressHandler = new AddressHandler(entityManager);
            EventLocationHandler eventLocationHandler = new EventLocationHandler(entityManager);
            EventHandler eventHandler = new EventHandler(entityManager);
            UserHandler userHandler = new UserHandler(entityManager);

            AddressMapper addressMapper = new AddressMapper();
            EventLocationMapper eventLocationMapper = new EventLocationMapper();
            EventMapper eventMapper = new EventMapper();
            UserMapper userMapper = new UserMapper();


*/
/*            // Create event location
            EventLocation eventLocation = new EventLocation();

            Optional<AddressEntity> optionalAddressEntity = addressHandler.findById(13L);
            if (optionalAddressEntity.isPresent()) {
                AddressEntity addressEntity = entityManager.merge(optionalAddressEntity.get());
                Address address = addressMapper.toModel(addressEntity);
                eventLocation.setAddress(address);
            } else {
                throw new NoSuchElementException("Address with ID 13 not found");
            }

            eventLocation.setName(faker.company().name());
            eventLocation.setCapacity(faker.number().numberBetween(10, 1000));
            EventLocationEntity eventLocationEntity = eventLocationHandler.save(eventLocationMapper.toEntity(eventLocation));
            System.out.println(eventLocationEntity);*//*



            // Create event
            Event event = new Event();
            event.setName(faker.company().name());

            // no real need for this
            // Find the user by ID safely
            Optional<UserEntity> optionalUserEntity = userHandler.findById(121L);
            if (optionalUserEntity.isPresent()) {
                UserEntity userEntity = optionalUserEntity.get();
                User user = userMapper.toModel(userEntity);
                event.setArtists(List.of(user)); // Set the user in the artists list
            } else {
                // Throw an exception if the user is not found
                throw new NoSuchElementException("User with ID 121 not found");
            }

            Optional<EventLocationEntity> optionalEventLocationEntity = eventLocationHandler.findById(2L);
            if (optionalEventLocationEntity.isPresent()) {
                EventLocationEntity eventLocationEntity1 = optionalEventLocationEntity.get();
                EventLocation eventLocation1 = eventLocationMapper.toModel(eventLocationEntity1);
                event.setLocation(eventLocation1);
            } else {
                throw new NoSuchElementException("Event location with ID 2 not found");
            }
            event.setBlockList(new ArrayList<User>());
            LocalDateTime startDate = LocalDateTime.now().plusYears(1);
            LocalDateTime endDate = startDate.plusHours(2);
            event.setStartDate(startDate);
            event.setEndDate(endDate);
            event.setDescription(faker.lorem().paragraph());
            event.setMaxTicketsPerUser(faker.number().numberBetween(1, 10));
            event.setTicketCount(faker.number().numberBetween(10, 1000));
            event.setTicketsSold(faker.number().numberBetween(0, (int) event.getTicketCount()));


            EventService eventService = new EventService(eventHandler);
            EventEntity entity = eventService.createEvent(event);

            System.out.println(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
*/
