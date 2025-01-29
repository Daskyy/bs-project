package com.thws.eventmanager.domain.models;


public class Address implements Model {
    int id = -1;
    String street;
    int no;
    String city;
    int zipCode;
    String country;

    public Address(String city, String country, int no, String street, int zipCode) {
        this.city = city;
        this.country = country;
        this.no = no;
        this.street = street;
        this.zipCode = zipCode;
    }

    public Address() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (this.id != -1) {
            throw new IllegalArgumentException("ID is already set");
        } else {
            this.id = id;
        }
    }
}
