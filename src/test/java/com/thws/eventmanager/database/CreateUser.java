package com.thws.eventmanager.database;

import com.github.javafaker.Faker;
import com.thws.eventmanager.domain.models.User;
import com.thws.eventmanager.infrastructure.components.persistence.PersistenceManager;
import com.thws.eventmanager.infrastructure.components.persistence.adapter.UserHandler;
import com.thws.eventmanager.domain.models.Permission;
import com.thws.eventmanager.infrastructure.components.persistence.entities.UserEntity;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.UserMapper;

import java.util.Optional;

public class CreateUser {
    public static void main(String[] args) {
        try(PersistenceManager persistenceManager = PersistenceManager.create()) {
            Faker faker = new Faker();
            UserHandler userHandler = new UserHandler(persistenceManager.getEntityManager());
            UserMapper userMapper = new UserMapper();
            User user = new User();
            user.setName(faker.name().fullName());
            user.setEmail(faker.internet().emailAddress());
            user.setPassword(faker.internet().password());
            user.setPermission(Permission.CUSTOMER);
            UserEntity userEntity = userHandler.save(userMapper.toEntity(user));
            System.out.println(userEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
