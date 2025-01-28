package com.thws.eventmanager.infrastructure.components.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class AddressEntity implements PersistenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "street", nullable = false)
    private String street;
    @Column(name = "no", nullable = false)
    private int no;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "zipCode", nullable = false)
    private int zipCode;
    @Column(name = "country", nullable = false)
    private String country;

    public AddressEntity() {}

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
