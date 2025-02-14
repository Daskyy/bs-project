package com.thws.eventmanager.infrastructure.GraphQL.auth;

import com.thws.eventmanager.domain.services.other.AuthenticationUseCase;
import com.thws.eventmanager.domain.exceptions.UnauthorizedException;
import graphql.kickstart.tools.GraphQLMutationResolver;

/*
This is just a demo implementation on how field definitions can be protected with a directive.
Authentication is currently NOT implemented completely (due to error handling and interactions between resolver and client), although you can query authenticate to receive a token.

in schema.graphqls:
directive @auth on FIELD_DEFINITION
@auth in front of a field to protect it with authentication
 */

public class AuthMutationResolver implements GraphQLMutationResolver {
    public String authenticate(String username, String password) throws UnauthorizedException {
        return AuthenticationUseCase.authenticate(username, password);
    }
}
