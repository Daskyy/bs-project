package com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperInputGQL;

import com.thws.eventmanager.infrastructure.GraphQL.InputModels.UserInput;
import com.thws.eventmanager.infrastructure.GraphQL.Models.UserGQL;

public class UserInputMapper  {
    public UserGQL toModelGQL(UserInput input) {
        if(input == null) return null;
        UserGQL userGQL = new UserGQL();
        userGQL.setName(input.getName());
        userGQL.setEmail(input.getEmail());
        userGQL.setPassword(input.getPassword());
        userGQL.setPermission(input.getPermission());
        userGQL.setId(input.getId());
        return userGQL;
    }


}
