package com.thws.eventmanager.debug;

import com.thws.eventmanager.infrastructure.components.persistence.adapter.EventHandler;
import com.thws.eventmanager.infrastructure.components.persistence.entities.EventEntity;

import java.util.List;

public class CriteriaTest {
    public static void main(String[] args) {
        try(EventHandler eventHandler = new EventHandler()) {
            List<EventEntity> events = eventHandler.searchByCriteria(List.of("name"), List.of("Stiedemann LLC"));
            events.forEach(System.out::println);
        }
    }
}
