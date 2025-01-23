package com.thws.eventmanager.infrastructure.components.persistence.mapper;
import com.thws.eventmanager.infrastructure.components.persistence.entities.*;
import com.thws.eventmanager.domain.models.*;

public class UserMapper extends Mapper<User, UserEntity>{
    @Override
    public User toModel(UserEntity userE){
        User user= new User();
        user.setName(userE.getName());
        user.setPermission(userE.getPermission());
        user.setEmail(userE.getEmail());
        user.setPassword(userE.getPassword());
        return user;
    }

    @Override
    public UserEntity toEntity(User user) {
       UserEntity  userE = new UserEntity();
       userE.setName(user.getName());
       userE.setEmail(user.getEmail());
       userE.setPassword(user.getPassword());
       userE.setPermission(user.getPermission());
       return userE;
    }


}
