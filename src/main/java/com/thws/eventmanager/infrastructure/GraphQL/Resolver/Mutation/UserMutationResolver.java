package com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mutation;

import com.thws.eventmanager.domain.models.User;
import com.thws.eventmanager.domain.services.db.UserService;
import com.thws.eventmanager.infrastructure.GraphQL.InputModels.UserInput;
import com.thws.eventmanager.infrastructure.GraphQL.Models.UserGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain.UserMapperGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperInputGQL.UserInputMapper;
import com.thws.eventmanager.infrastructure.components.persistence.adapter.UserHandler;
import com.thws.eventmanager.infrastructure.components.persistence.entities.UserEntity;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.UserMapper;
import graphql.kickstart.tools.GraphQLMutationResolver;


public class  UserMutationResolver implements GraphQLMutationResolver {
    UserMapper userMapper = new UserMapper();
    UserMapperGQL userMapperGQL = new UserMapperGQL();
    UserInputMapper userInputMapper = new UserInputMapper();

    public UserGQL createUser(UserInput input) {
        User user = userMapperGQL.toModel(userInputMapper.toModelGQL(input));

        UserService userService = new UserService();

        UserEntity userEntity = userService.createUser(user);
        user = userMapper.toModel(userEntity);
        return userMapperGQL.toModelGQL(user);
    }

    public UserGQL updateUser(String id, UserInput input) {

        try (UserHandler userHandler = new UserHandler()) {
            UserService userService = new UserService();

            UserEntity loaded = userHandler.findById(Long.parseLong(id)).orElseThrow(); // TODO: What to do here

            User user = userMapper.toModel(loaded);
            if (input.getName() != null) user.setName(input.getName());
            if (input.getEmail() != null) user.setEmail(input.getEmail());
            if (input.getPassword() != null) user.setPassword(input.getPassword());
            if (input.getPermission() != null) user.setPermission(input.getPermission().to());

            UserEntity userEntity = userService.createUser(user);
            user = userMapper.toModel(userEntity);
            return userMapperGQL.toModelGQL(user);
        }
    }

    public UserGQL deleteUser(String id) {
        try (UserHandler userHandler = new UserHandler()) {
            UserService userService = new UserService();
            UserEntity userEntity = userHandler.findById(Long.parseLong(id)).orElseThrow(); // TODO: what to do here
            userHandler.deleteById(Long.parseLong(id));

            return userMapperGQL.toModelGQL(userMapper.toModel(userEntity));
        }
    }
}
