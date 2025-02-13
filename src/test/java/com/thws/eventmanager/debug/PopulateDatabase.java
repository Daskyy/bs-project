package com.thws.eventmanager.debug;

import com.github.javafaker.Faker;
import com.thws.eventmanager.domain.models.*;
import com.thws.eventmanager.domain.services.models.EventService;
import com.thws.eventmanager.domain.services.models.UserService;
import com.thws.eventmanager.domain.services.models.VoucherService;
import com.thws.eventmanager.infrastructure.components.persistence.adapter.UserHandler;
import com.thws.eventmanager.infrastructure.components.persistence.adapter.VoucherHandler;
import com.thws.eventmanager.infrastructure.components.persistence.entities.UserEntity;
import com.thws.eventmanager.infrastructure.components.persistence.entities.VoucherEntity;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.UserMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PopulateDatabase {
    static UserService userService = new UserService();
    static EventService eventService = new EventService();
    static VoucherService voucherService = new VoucherService();
    static Faker faker = new Faker();


    public static void main(String[] args) {
        createUsers();
        createEvents();
        createVouchers();
    }
    public static void createUsers() {
        for (int i = 0; i < 100; i++) {
            UserMapper userMapper = new UserMapper();
            User user = new User();
            user.setName(faker.name().fullName());
            user.setEmail(faker.internet().emailAddress());
            user.setPassword(faker.internet().password());
            user.setPermission(Permission.CUSTOMER);
            UserEntity userEntity = userService.createUser(user);
            System.out.println(userEntity);
        }
        for (int i = 0; i < 20; i++) {
            UserMapper userMapper = new UserMapper();
            User user = new User();
            user.setName(faker.name().fullName());
            user.setEmail(faker.internet().emailAddress());
            user.setPassword(faker.internet().password());
            user.setPermission(Permission.ARTIST);
            UserEntity userEntity = userService.createUser(user);
            System.out.println(userEntity);
        }
        for (int i = 0; i < 10; i++) {
            UserMapper userMapper = new UserMapper();
            User user = new User();
            user.setName(faker.name().fullName());
            user.setEmail(faker.internet().emailAddress());
            user.setPassword(faker.internet().password());
            user.setPermission(Permission.STAFF);
            UserEntity userEntity = userService.createUser(user);
            System.out.println(userEntity);
        }
    }

    public static void createEvents() {
        for (int i = 0; i < 50; i++) {
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
            event.setDescription(faker.lorem().sentence(10));
            event.setTicketPrice(faker.number().numberBetween(2000, 50000));

            eventService.createEvent(event);
        }
    }

    public static void createVouchers() {
        for (int i = 0; i < 20; i++) {
            voucherService.createVoucher(faker.code().ean13(), faker.number().numberBetween(1000, 5000), faker.number().numberBetween(1, 10));
        }
    }
}
