package com.thws.eventmanager.infrastructure.GraphQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mutation.*;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Query.*;
import graphql.kickstart.servlet.GraphQLConfiguration;
import graphql.kickstart.servlet.GraphQLHttpServlet;
import graphql.kickstart.tools.SchemaParser;
import graphql.schema.GraphQLSchema;
import org.apache.commons.io.IOUtils;

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
            // Lies dein schema.graphqls aus resources
            String schemaString = IOUtils.toString(
                    this.getClass().getResourceAsStream("/schema.graphqls"),
                    "UTF-8"
            );

            // Registriere sämtliche Resolver, die du hast
            return SchemaParser.newParser()
                    .schemaString(schemaString)
                    .resolvers(
                            new EventQueryResolver(),
                            new EventMutationResolver(),
                            new UserQueryResolver(),
                            new UserMutationResolver()
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
