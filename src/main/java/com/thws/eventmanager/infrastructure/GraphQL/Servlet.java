package com.thws.eventmanager.infrastructure.GraphQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mutation.*;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Query.*;
import com.thws.eventmanager.infrastructure.GraphQL.auth.AuthMutationResolver;
import graphql.kickstart.servlet.GraphQLConfiguration;
import graphql.kickstart.servlet.GraphQLHttpServlet;
import graphql.kickstart.tools.SchemaParser;
import graphql.schema.GraphQLSchema;
import jakarta.servlet.annotation.WebServlet;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
@WebServlet(name= "EventHim", urlPatterns = {"/graphql/*"}, loadOnStartup = 1)
public class Servlet extends GraphQLHttpServlet{
    public Servlet() {
        System.out.println("GraphQL Servlet starts");
    }

    @Override
    protected GraphQLConfiguration getConfiguration() {
        return GraphQLConfiguration
                .with(createSchema())
                .build();
    }

    private GraphQLSchema createSchema() {
        try {
            String schemaString = IOUtils.toString(
                    this.getClass().getResourceAsStream("/schema.graphqls")
            );

            return SchemaParser.newParser()
                    .schemaString(schemaString)
                    .resolvers(
                            new EventQueryResolver(),
                            new EventMutationResolver(),
                            new UserQueryResolver(),
                            new UserMutationResolver(),
                            new PaymentResolver(),
                            new AddressMutationResolver(),
                            new AddressQueryResolver(),
                            new EventLocationQueryResolver(),
                            new AuthMutationResolver(),
                            new EventLocationMutationResolver(),
                            new VoucherMutationResolver(),
                            new VoucherQueryResolver()
                            // ggf. weitere
                    )
                    .build()
                    .makeExecutableSchema();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
