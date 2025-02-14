package com.thws.eventmanager.domain.models;

import java.util.Objects;

public class Address implements Model {
    long id = -1;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        if (this.id != -1) {
            throw new IllegalArgumentException("ID is already set");
        } else {
            this.id = id;
        }
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Address other = (Address) obj;

        //if id's have been assigned
        if (this.id != -1 && other.id != -1) {
            return this.id == other.id;
        }

        //If any ID is not assigned, compare the other attributes
        return id == other.id &&
               no == other.no &&
               zipCode == other.zipCode &&
               Objects.equals(street, other.street) &&
               Objects.equals(city, other.city) &&
               Objects.equals(country, other.country);
    }

    @Override
    public int hashCode() {
        // If the id is not -1, include it in the hash
        return Objects.hash(id == -1 ? 0 : id, street, no, city, zipCode, country);
    }

}
