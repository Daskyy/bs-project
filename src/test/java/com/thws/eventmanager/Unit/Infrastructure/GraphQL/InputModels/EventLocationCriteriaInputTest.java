package com.thws.eventmanager.Unit.Infrastructure.GraphQL.InputModels;

import com.thws.eventmanager.infrastructure.GraphQL.InputModels.EventLocationCriteriaInput;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EventLocationCriteriaInputTest {
    @Test
    void getCriteria_CorrectCriteria() {
        EventLocationCriteriaInput eventLocation = new EventLocationCriteriaInput();

        // Test with all fields filled
        eventLocation.setAddressentity_id("123");
        eventLocation.setName("Event Location");
        eventLocation.setCapacity(100);

        List<String> criteria = eventLocation.getCriteria();
        assertEquals(3, criteria.size());
        assertTrue(criteria.contains("addressentity_id"));
        assertTrue(criteria.contains("name"));
        assertTrue(criteria.contains("capacity"));

        // Test with some fields null or empty
        eventLocation.setName(null); // name is null
        criteria = eventLocation.getCriteria();
        assertEquals(2, criteria.size());
        assertTrue(criteria.contains("addressentity_id"));
        assertTrue(criteria.contains("capacity"));

        // Test with empty string fields
        eventLocation.setName(""); // name is empty
        criteria = eventLocation.getCriteria();
        assertEquals(2, criteria.size());
        assertTrue(criteria.contains("capacity"));

        // Test with null addressentity_id
        eventLocation.setAddressentity_id(null); // addressentity_id is null
        criteria = eventLocation.getCriteria();
        assertEquals(1, criteria.size());
        assertTrue(criteria.contains("capacity"));

        // Test with null capacity
        eventLocation.setCapacity(null); // capacity is null
        criteria = eventLocation.getCriteria();
        assertEquals(0, criteria.size());
    }

    @Test
    void getValues_CorrectValues() {
        EventLocationCriteriaInput eventLocation = new EventLocationCriteriaInput();

        // Test with all fields filled
        eventLocation.setAddressentity_id("123");
        eventLocation.setName("Event Location");
        eventLocation.setCapacity(100);

        List<Object> values = eventLocation.getValues();
        assertEquals(3, values.size());
        assertTrue(values.contains("123"));
        assertTrue(values.contains("Event Location"));
        assertTrue(values.contains(100));

        // Test with some fields null or empty
        eventLocation.setName(null); // name is null
        values = eventLocation.getValues();
        assertEquals(2, values.size());
        assertTrue(values.contains("123"));
        assertTrue(values.contains(100));

        eventLocation.setName(""); // name is empty
        values = eventLocation.getValues();
        assertEquals(2, values.size());
        assertTrue(values.contains("123"));
        assertTrue(values.contains(100));

        // Test with addressentity_id null
        eventLocation.setAddressentity_id(null);
        values = eventLocation.getValues();
        assertEquals(1, values.size());
        assertTrue(values.contains(100));

        // Test with null capacity
        eventLocation.setCapacity(null); // capacity is null
        values = eventLocation.getValues();
        assertEquals(0, values.size());
    }

    @Test
    void getCriteria_Empty() {
        EventLocationCriteriaInput eventLocation = new EventLocationCriteriaInput();
        List<String> criteria = eventLocation.getCriteria();
        assertTrue(criteria.isEmpty());
    }

    @Test
    void getValues_Empty() {
        EventLocationCriteriaInput eventLocation = new EventLocationCriteriaInput();
        List<Object> values = eventLocation.getValues();
        assertTrue(values.isEmpty());
    }
}