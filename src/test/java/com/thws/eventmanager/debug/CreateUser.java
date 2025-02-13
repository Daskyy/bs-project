package com.thws.eventmanager.debug;

import com.github.javafaker.Faker;
import com.thws.eventmanager.domain.models.User;
import com.thws.eventmanager.infrastructure.components.persistence.adapter.UserHandler;
import com.thws.eventmanager.domain.models.Permission;
import com.thws.eventmanager.infrastructure.components.persistence.entities.UserEntity;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.UserMapper;

public class CreateUser {
    public static void main(String[] args) {
        try(UserHandler userHandler = new UserHandler()) {
            Faker faker = new Faker();
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

