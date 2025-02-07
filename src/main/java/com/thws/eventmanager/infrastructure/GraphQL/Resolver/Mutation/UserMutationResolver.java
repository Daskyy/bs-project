package com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mutation;

import com.thws.eventmanager.domain.usecases.UserService;
import com.thws.eventmanager.infrastructure.GraphQL.InputModels.UserInput;
import com.thws.eventmanager.infrastructure.GraphQL.Models.UserGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain.UserMapperGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperInputGQL.UserInputMapper;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.jetbrains.annotations.NotNull;


public class UserMutationResolver implements GraphQLMutationResolver {

    public UserGQL createUser(UserInput input) {
        System.out.println("createUser");
        UserGQL newUser = UserInputMapper.toModelGQL(input);

        UserMapperGQL umGQL = new UserMapperGQL();
        UserService us = new UserService();

        return umGQL.toModelGQL(
                us.createUser(umGQL.toModel(newUser)));
    }
}
