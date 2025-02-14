package com.thws.eventmanager.domain.models;

public class User implements Model {
    private String name;
    private String email;
    private String password;
    private Permission permission;
    private long id = -1;

    public User() {
    }

    public User(String name, String email, String password, Permission permission) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.permission = permission;
    }

    public String getEmail() {
            return email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        if (this.id != -1) {
            throw new IllegalArgumentException("ID is already set");
        } else {
            this.id = id;
        }
    }
}
