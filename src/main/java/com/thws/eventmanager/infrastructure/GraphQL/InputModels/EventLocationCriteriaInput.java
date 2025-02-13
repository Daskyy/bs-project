package com.thws.eventmanager.infrastructure.GraphQL.InputModels;

import java.util.ArrayList;
import java.util.List;

public class EventLocationCriteriaInput {
    private String addressentity_id;
    private String name;
    private Integer capacity;

    public EventLocationCriteriaInput() {
    }

    public String getAddressentity_id() {
        return addressentity_id;
    }

    public void setAddressentity_id(String addressentity_id) {
        this.addressentity_id = addressentity_id;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<String> getCriteria() {
        List<String> criteria = new ArrayList<>();
        if (addressentity_id != null) {
            criteria.add("addressentity_id");
        }
        if (name != null && !name.isBlank()) {
            criteria.add("name");
        }
        if (capacity != null) {
            criteria.add("capacity");
        }

        return criteria;
    }


    public List<Object> getValues() {
        List<Object> values = new ArrayList<>();
        if (addressentity_id != null) {
            values.add(addressentity_id);
        }
        if (name != null && !name.isBlank()) {
            values.add(name);
        }
        if (capacity != null) {
            values.add(capacity);
        }

        return values;
    }
}
