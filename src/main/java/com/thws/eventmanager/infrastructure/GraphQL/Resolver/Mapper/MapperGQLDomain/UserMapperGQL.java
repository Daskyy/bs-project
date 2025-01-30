package com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain;

import com.thws.eventmanager.domain.models.User;
import com.thws.eventmanager.infrastructure.GraphQL.Models.UserGQL;

public class UserMapperGQL extends Mapper<User, UserGQL> {
    EnumMapper enumMapper = new EnumMapper();
    @Override
    public User toModel(UserGQL userGQL) {
        if(userGQL == null) return null;
        User u= new User();
        u.setName(userGQL.getName());
        u.setEmail(userGQL.getEmail());
        u.setPassword(userGQL.getPassword());
        u.setPermission(enumMapper.toModel(userGQL.getPermission()));
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
        gql.setPermission(enumMapper.toModelGQL(user.getPermission()));
        return gql;
    }
}
