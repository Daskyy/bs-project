package com.thws.eventmanager.infrastructure.GraphQL.InputModels;

import java.util.ArrayList;
import java.util.List;

public class AddressCriteriaInput {
    String country;
    String city;
    String street;
    int zipCode;

    public AddressCriteriaInput() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public List<String> getCriteria() {
        List<String> criteria = new ArrayList<>();
        if (country != null && !country.isBlank()) {
            criteria.add("country");
        }
        if (city != null && !city.isBlank()) {
            criteria.add("city");
        }
        if (street != null && !street.isBlank()) {
            criteria.add("street");
        }
        if (zipCode > 0) { // Assuming zipCode is positive
            criteria.add("zipCode");
        }
        return criteria;
    }


    public List<Object> getValues() {
        List<Object> values = new ArrayList<>();
        if (country != null && !country.isBlank()) {
            values.add(country);
        }
        if (city != null && !city.isBlank()) {
            values.add(city);
        }
        if (street != null && !street.isBlank()) {
            values.add(street);
        }
        if (zipCode > 0) { // Assuming zipCode is positive
            values.add(zipCode);
        }

        return values;
    }
}
