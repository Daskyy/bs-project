package com.thws.eventmanager.database;

import com.thws.eventmanager.infrastructure.adapter.persistence.UserHandler;

public class GetAllUsers {
    public static void main(String[] args) {
        // Initialize the database handler
        UserHandler UserHandler = new UserHandler();

        try {
            UserHandler.getAllUsers().forEach(user -> System.out.println(
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
