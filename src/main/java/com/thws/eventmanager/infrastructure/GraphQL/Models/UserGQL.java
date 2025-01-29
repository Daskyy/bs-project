package com.thws.eventmanager.infrastructure.GraphQL.Models;

public class UserGQL {
    private String id;
    private String name;
    private String email;
    private String password;     // Achtung: Sicherheitsaspekt
    private PermissionGQL permission;

    public UserGQL() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public PermissionGQL getPermission() {
        return permission;
    }

    public void setPermission(PermissionGQL permission) {
        this.permission = permission;
    }
}
