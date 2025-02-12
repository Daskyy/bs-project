package com.thws.eventmanager.infrastructure.components.persistence.mapper;
import com.thws.eventmanager.infrastructure.components.persistence.entities.*;
import com.thws.eventmanager.domain.models.*;

public class UserMapper extends Mapper<User, UserEntity>{
    @Override
    public User toModel(UserEntity entity){
        User user= new User();
        user.setName(entity.getName());
        user.setPermission(entity.getPermission());
        user.setEmail(entity.getEmail());
        user.setPassword(entity.getPassword());
        user.setId(entity.getId());
        return user;
    }

    @Override
    public UserEntity toEntity(User user) {
       UserEntity entity = new UserEntity();

       entity.setName(user.getName());
       entity.setEmail(user.getEmail());
       entity.setPassword(user.getPassword());
       entity.setPermission(user.getPermission());

        if(user.getId() != -1) {
            entity.setId(user.getId());
        }

       return entity;
    }
}
