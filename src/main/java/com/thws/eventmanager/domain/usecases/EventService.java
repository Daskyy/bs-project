package com.thws.eventmanager.domain.usecases;

import com.thws.eventmanager.domain.exceptions.InvalidEventException;
import com.thws.eventmanager.domain.models.Event;
import com.thws.eventmanager.domain.models.User;
import com.thws.eventmanager.domain.port.in.EventServiceInterface;
import com.thws.eventmanager.domain.port.out.GenericPersistenceOutport;
import com.thws.eventmanager.infrastructure.components.persistence.adapter.EventHandler;
import com.thws.eventmanager.infrastructure.components.persistence.entities.EventEntity;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.EventMapper;

import java.time.LocalDateTime;

public class EventService implements EventServiceInterface {
    private final EventMapper eventMapper = new EventMapper();

    public EventService() {
    }

    @Override
    public EventEntity createEvent(Event event) {
        validateEvent(event);
        try(EventHandler eventHandler = new EventHandler()) {
            return eventHandler.save(eventMapper.toEntity(event));
        } catch (Exception e) {
            throw new InvalidEventException("Failed to create event.");
        }
    }

    @Override
    public void validateEvent(Event event) {
        if (event.getName() == null || event.getName().trim().isEmpty()) {
            throw new InvalidEventException("Event name cannot be null or empty.");
        }

        if (event.getDescription() == null || event.getDescription().trim().isEmpty()) {
            throw new InvalidEventException("Event description cannot be null or empty.");
        }

        if (event.getTicketCount() <= 0) {
            throw new InvalidEventException("Ticket count must be greater than zero.");
        }

        if (event.getTicketsSold() < 0) {
            throw new InvalidEventException("Tickets sold cannot be negative.");
        }

        if (event.getTicketsSold() > event.getTicketCount()) {
            throw new InvalidEventException("Tickets sold cannot exceed the total ticket count.");
        }

        if (event.getMaxTicketsPerUser() <= 0) {
            throw new InvalidEventException("Max tickets per user must be greater than zero.");
        }

        if (event.getArtists() == null || event.getArtists().isEmpty()) {
            throw new InvalidEventException("Event must have at least one artist.");
        }

        if (event.getLocation() == null) {
            throw new InvalidEventException("Event location cannot be null.");
        }

        if (event.getBlockList() == null) {
            throw new InvalidEventException("Block list cannot be null. Use an empty list if no users are blocked.");
        }

        if (event.getStartDate() == null) {
            throw new InvalidEventException("Start date cannot be null.");
        }

        if (event.getEndDate() == null) {
            throw new InvalidEventException("End date cannot be null.");
        }

        if (event.getStartDate().isAfter(event.getEndDate())) {
            throw new InvalidEventException("Start date cannot be after the end date.");
        }

        if (event.getStartDate().isBefore(LocalDateTime.now())) {
            throw new InvalidEventException("Start date cannot be in the past.");
        }
    }
}