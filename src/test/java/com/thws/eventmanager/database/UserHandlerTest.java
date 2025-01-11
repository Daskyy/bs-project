package com.thws.eventmanager.database;

import com.github.javafaker.Faker;
import com.thws.eventmanager.domain.models.Permission;
import com.thws.eventmanager.domain.models.User;
import com.thws.eventmanager.infrastructure.adapter.persistence.UserHandler;

import java.util.List;

public class UserHandlerTest {

    public static void main(String[] args) {
        // Initialize the database handler
        UserHandler UserHandler = new UserHandler();

        try {
            Faker faker = new Faker();

            for (int i = 0; i < 10; i++) {
                User user = new User(
                        faker.name().fullName(),
                        faker.internet().emailAddress(),
                        faker.internet().password(),
                        Permission.CUSTOMER
                );
                UserHandler.saveUser(user);
            }
            for (int i = 0; i < 10; i++) {
                User user = new User(
                        faker.name().fullName(),
                        faker.internet().emailAddress(),
                        faker.internet().password(),
                        Permission.ARTIST
                );
                UserHandler.saveUser(user);
            }

            List<User> users = UserHandler.getAllUsers();
            users.forEach(user -> System.out.println(
                    "ID: " + user.getId() +
                            ", Name: " + user.getName() +
                            ", Email: " + user.getEmail()
            ));

        } finally {
            // Close the database connection
            UserHandler.close();
        }
    }
}
