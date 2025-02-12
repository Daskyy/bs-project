
package com.thws.eventmanager.debug;

import com.thws.eventmanager.infrastructure.components.persistence.adapter.UserHandler;

public class GetAllUsers {
    public static void main(String[] args) {


        try (UserHandler userHandler = new UserHandler()) {
            userHandler.findAll().forEach(System.out::println);
    } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

