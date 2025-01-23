package com.thws.eventmanager.database;


import com.thws.eventmanager.infrastructure.components.persistence.PersistenceManager;
import com.thws.eventmanager.infrastructure.components.persistence.adapter.UserHandler;

public class GetAllUsers {
    public static void main(String[] args) {
        try (PersistenceManager persistenceManager = PersistenceManager.create()) {
            UserHandler userHandler = new UserHandler(persistenceManager.getEntityManager());
            userHandler.findAll().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
