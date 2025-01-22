package com.thws.eventmanager.infrastructure.components.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class AddressEntity implements PersistenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "street", nullable = false)
    String street;
    @Column(name = "no", nullable = false)
    int no;
    @Column(name = "city", nullable = false)
    String city;
    @Column(name = "zipCode", nullable = false)
    int zipCode;
    @Column(name = "country", nullable = false)
    String country;

    public AddressEntity(String city, String country, int no, String street, int zipCode) {
        this.city = city;
        this.country = country;
        this.no = no;
        this.street = street;
        this.zipCode = zipCode;
    }

    public AddressEntity() {

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
}
