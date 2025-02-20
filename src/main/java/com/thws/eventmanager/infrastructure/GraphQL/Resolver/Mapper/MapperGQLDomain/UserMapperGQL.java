package com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain;

import com.thws.eventmanager.domain.models.User;
import com.thws.eventmanager.infrastructure.GraphQL.Models.PermissionGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Models.UserGQL;
import com.thws.eventmanager.infrastructure.components.persistence.adapter.UserHandler;
import com.thws.eventmanager.infrastructure.components.persistence.entities.UserEntity;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.UserMapper;

import java.util.ArrayList;
import java.util.List;

public class UserMapperGQL extends Mapper<User, UserGQL> {
    UserMapper userMapper = new UserMapper();

    @Override
    public User toModel(UserGQL userGQL) {
        if(userGQL == null) return null;
        User u= new User();
        u.setName(userGQL.getName());
        u.setEmail(userGQL.getEmail());
        u.setPassword(userGQL.getPassword());
        u.setPermission(userGQL.getPermission().to());
        if(userGQL.getId()!=null) u.setId((long)Integer.parseInt(userGQL.getId()));

        return u;
    }

    @Override
    public UserGQL toModelGQL(User user) {
        if(user == null) return null;
        UserGQL gql = new UserGQL();
        gql.setId(String.valueOf(user.getId()));
        gql.setName(user.getName());
        gql.setEmail(user.getEmail());
        gql.setPassword(user.getPassword());
        gql.setPermission(PermissionGQL.from(user.getPermission()));
        if(user.getId()!=-1) gql.setId(String.valueOf(user.getId()));   

        return gql;
    }

    public List<UserGQL> toUserGQLList(List<String> idList){
        try(UserHandler userHandler = new UserHandler()){
            List<UserEntity> userList = new ArrayList<>();
            for(String id : idList){
                userList.add(userHandler.findById(Long.parseLong(id)).orElse(null));
            }
            return userList.stream().map(userMapper::toModel).map(this::toModelGQL).toList();
        }

    }
}
