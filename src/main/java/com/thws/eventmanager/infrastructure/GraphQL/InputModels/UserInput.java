package com.thws.eventmanager.infrastructure.GraphQL.InputModels;

import com.thws.eventmanager.infrastructure.GraphQL.Models.PermissionGQL;

public class UserInput {
    private String name;
    private String email;
    private String password;
    private PermissionGQL permission;
    private String id ="-1";
    public UserInput() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PermissionGQL getPermission() {
        return permission;
    }

    public void setPermission(PermissionGQL permissionGQL) {
        this.permission = permissionGQL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
