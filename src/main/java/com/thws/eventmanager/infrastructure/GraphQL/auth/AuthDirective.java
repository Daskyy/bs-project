package com.thws.eventmanager.infrastructure.GraphQL.auth;

import com.thws.eventmanager.domain.exceptions.UnauthorizedException;
import com.thws.eventmanager.domain.services.other.AuthenticationUseCase;
import graphql.kickstart.servlet.context.GraphQLServletContext;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.idl.SchemaDirectiveWiring;
import graphql.schema.idl.SchemaDirectiveWiringEnvironment;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;

/*
This is just a demo implementation on how field definitions can be protected with a directive.
Authentication is currently NOT implemented completely (due to error handling and interactions between resolver and client), although you can query authenticate to receive a token.

in schema.graphqls:
directive @auth on FIELD_DEFINITION
@auth in front of a field to protect it with authentication
 */


public class AuthDirective implements SchemaDirectiveWiring {

    @Override
    public GraphQLFieldDefinition onField(SchemaDirectiveWiringEnvironment<GraphQLFieldDefinition> environment) {
        DataFetcher<?> originalFetcher = environment.getCodeRegistry().getDataFetcher(environment.getFieldsContainer(), environment.getElement());

        DataFetcher<?> authFetcher = dataFetchingEnvironment -> {
            validateAuth(dataFetchingEnvironment);
            return originalFetcher.get(dataFetchingEnvironment);
        };

        environment.getCodeRegistry().dataFetcher(environment.getFieldsContainer(), environment.getElement(), authFetcher);
        return environment.getElement();
    }

    private void validateAuth(DataFetchingEnvironment environment) throws UnauthorizedException {
        GraphQLServletContext servletContext = environment.getContext();
        HttpServletRequest request = servletContext.getHttpServletRequest();

        if (request == null) {
            throw new UnauthorizedException("No HTTP Request Found");
        }

        String authHeader = Optional.ofNullable(request.getHeader("Authorization"))
                .orElseThrow(() -> new UnauthorizedException("Missing Authorization Header"));

        if (!authHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("Invalid Authorization Format. Expected 'Bearer <token>'");
        }

        String token = authHeader.substring(7);

        AuthenticationUseCase auth = AuthenticationUseCase.getInstance();
        auth.token = token;
        if (!auth.isAuthorized()) {
            throw new UnauthorizedException("Invalid or Expired Token");
        }
    }
}
