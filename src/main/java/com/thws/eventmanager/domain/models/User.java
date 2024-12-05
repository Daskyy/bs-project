package com.thws.eventmanager.domain.models;

public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Permission permission;

    public User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
            return email;
        }

}
