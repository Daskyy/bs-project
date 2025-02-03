package com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperInputGQL;

import com.thws.eventmanager.domain.models.User;
import com.thws.eventmanager.infrastructure.GraphQL.InputModels.UserInput;
import com.thws.eventmanager.infrastructure.GraphQL.Models.UserGQL;

public class UserInputMapper  {
    public static UserGQL toModelGQL(UserInput user) {
        if(user == null) return null;
        UserGQL gql = new UserGQL();
        gql.setName(user.getName());
        gql.setEmail(user.getEmail());
        gql.setPassword(user.getPassword());
        gql.setPermission(user.getPermissionGQL());
        return gql;
    }


}
