package com.thws.eventmanager.database;

import com.github.javafaker.Faker;
import com.thws.eventmanager.domain.models.Address;
import com.thws.eventmanager.domain.models.Event;
import com.thws.eventmanager.domain.models.EventLocation;
import com.thws.eventmanager.domain.models.User;
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
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

// creating adresses and event locations works, but creating events gives me stupid errors
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

            // Create address
            Address address = new Address();
            address.setZipCode(1337);
            address.setCity(faker.address().city());
            address.setStreet(faker.address().streetName());
            address.setNo(Integer.parseInt(faker.address().buildingNumber()));
            address.setCountry(faker.address().country());
            AddressEntity addressEntity = addressHandler.save(addressMapper.toEntity(address));
            System.out.println(addressEntity);

            // Create event location
            EventLocation eventLocation = new EventLocation();
            eventLocation.setAddress(address);
            eventLocation.setName(faker.company().name());
            eventLocation.setCapacity(faker.number().numberBetween(10, 1000));
            EventLocationEntity eventLocationEntity = eventLocationHandler.save(eventLocationMapper.toEntity(eventLocation));
            System.out.println(eventLocationEntity);


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


            event.setBlockList(null);
            LocalDateTime startDate = LocalDateTime.now().plusYears(1);
            LocalDateTime endDate = startDate.plusHours(2);
            event.setStartDate(startDate);
            event.setEndDate(endDate);
            event.setDescription(faker.lorem().paragraph());
            event.setMaxTicketsPerUser(faker.number().numberBetween(1, 10));
            event.setTicketCount(faker.number().numberBetween(10, 1000));
            event.setTicketsSold(faker.number().numberBetween(0, (int) event.getTicketCount()));

            // this is broken
            EventEntity entity = eventMapper.toEntity(event);
            entity.setLocation(eventLocationEntity);
            EventEntity eventEntity = eventHandler.save(entity);

            System.out.println(eventEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
