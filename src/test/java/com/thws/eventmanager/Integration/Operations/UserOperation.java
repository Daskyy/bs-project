package com.thws.eventmanager.Integration.Operations;

import com.thws.eventmanager.infrastructure.GraphQL.Models.UserGQL;
import com.thws.eventmanager.infrastructure.GraphQL.InputModels.UserInput;

public class UserOperation extends AbstractAPIOperation {
    
    public UserGQL createUser(UserInput input) throws Exception {
        String mutation = String.format(
            "mutation createUser { " +
            "  createUser(input: { name: \"%s\", email: \"%s\", password: \"%s\", permission: %s }) { " +
            "    id name email permission " +
            "  } " +
            "}", 
            input.getName(), input.getEmail(), input.getPassword(), input.getPermission());
        return executeQuery(mutation, "createUser", UserGQL.class);
    }

    public UserGQL updateUser(String id, UserInput input) throws Exception {
        String mutation = String.format(
            "mutation updateUser { " +
            "  updateUser(id: \"%s\", input: { name: \"%s\", email: \"%s\" }) { " +
            "    id name email permission " +
            "  } " +
            "}", id, input.getName(), input.getEmail());
        return executeQuery(mutation, "updateUser", UserGQL.class);
    }

    public UserGQL deleteUser(String id) throws Exception {
        String mutation = String.format(
            "mutation deleteUser { " +
            "  deleteUser(id: \"%s\") { " +
            "    id " +
            "  } " +
            "}", id);
        return executeQuery(mutation, "deleteUser", UserGQL.class);
    }

    public UserGQL queryUser(String id) throws Exception {
        String query = String.format(
            "query getUser { " +
            "  user(id: \"%s\") { id name email permission } " +
            "}", id);
        return executeQuery(query, "user", UserGQL.class);
    }
    
    public UserGQL[] queryUsers() throws Exception {
        String query = "query getUsers { " +
                       "  users { id name email permission } " +
                       "}";
        return executeQuery(query, "users", UserGQL[].class);
    }
} 