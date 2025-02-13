package com.thws.eventmanager.Unit.Infrastructure.GraphQL.InputModels;

import com.thws.eventmanager.infrastructure.GraphQL.InputModels.AddressCriteriaInput;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class AddressCriteriaInputTest {
    @Test
    void getCriteria_CorrectCriteria() {
        AddressCriteriaInput address = new AddressCriteriaInput();

        // Test with all fields filled
        address.setCountry("USA");
        address.setCity("New York");
        address.setStreet("5th Avenue");
        address.setZipCode(10001);

        List<String> criteria = address.getCriteria();
        assertEquals(4, criteria.size());
        assertTrue(criteria.contains("country"));
        assertTrue(criteria.contains("city"));
        assertTrue(criteria.contains("street"));
        assertTrue(criteria.contains("zipCode"));

        // Test with some fields null or empty
        address.setStreet(null); // street is null
        criteria = address.getCriteria();
        assertEquals(3, criteria.size());
        assertTrue(criteria.contains("country"));
        assertTrue(criteria.contains("city"));
        assertTrue(criteria.contains("zipCode"));

        // Test with empty string fields
        address.setCity(""); // city is empty
        criteria = address.getCriteria();
        assertEquals(2, criteria.size());
        assertTrue(criteria.contains("country"));
        assertTrue(criteria.contains("zipCode"));

        // Test with zipCode <= 0
        address.setZipCode(0); // zipCode is zero
        criteria = address.getCriteria();
        assertEquals(1, criteria.size());
        assertTrue(criteria.contains("country"));
    }

    @Test
    void getValues_CorrectValues() {
        AddressCriteriaInput address = new AddressCriteriaInput();

        // Test with all fields filled
        address.setCountry("USA");
        address.setCity("New York");
        address.setStreet("5th Avenue");
        address.setZipCode(10001);

        List<Object> values = address.getValues();
        assertEquals(4, values.size());
        assertTrue(values.contains("USA"));
        assertTrue(values.contains("New York"));
        assertTrue(values.contains("5th Avenue"));
        assertTrue(values.contains(10001));

        // Test with some fields null or empty
        address.setStreet(null); // street is null
        values = address.getValues();
        assertEquals(3, values.size());
        assertTrue(values.contains("USA"));
        assertTrue(values.contains("New York"));
        assertTrue(values.contains(10001));

        // Test with empty string fields
        address.setCity(""); // city is empty
        values = address.getValues();
        assertEquals(2, values.size());
        assertTrue(values.contains("USA"));
        assertTrue(values.contains(10001));

        // Test with zipCode <= 0
        address.setZipCode(0); // zipCode is zero
        values = address.getValues();
        assertEquals(1, values.size());
        assertTrue(values.contains("USA"));
    }

    @Test
    void getCriteria_Empty() {
        AddressCriteriaInput address = new AddressCriteriaInput();
        List<String> criteria = address.getCriteria();
        assertTrue(criteria.isEmpty());
    }

    @Test
    void getValues_Empty() {
        AddressCriteriaInput address = new AddressCriteriaInput();
        List<Object> values = address.getValues();
        assertTrue(values.isEmpty());
    }

}