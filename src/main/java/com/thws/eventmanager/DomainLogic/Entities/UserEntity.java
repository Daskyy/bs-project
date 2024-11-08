package com.thws.eventmanager.DomainLogic.Entities;

public class UserEntity {
    private Long id;
    private String name;
    private String email;
    private String password;

    public UserEntity() {
    }

    public UserEntity(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
            return email;
        }

}
