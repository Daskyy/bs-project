package com.thws.eventmanager.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestDB {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://db.backend.heppify.de:5432/eventmanager";
        String user = "javabackend";
        String password = "kc82jdrjos";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("✅ Database Connection Successful!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

