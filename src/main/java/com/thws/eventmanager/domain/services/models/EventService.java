package com.thws.eventmanager.domain.services.models;

import com.thws.eventmanager.domain.exceptions.InvalidEventException;
import com.thws.eventmanager.domain.models.Event;
import com.thws.eventmanager.domain.models.Ticket;
import com.thws.eventmanager.domain.models.User;
import com.thws.eventmanager.domain.port.in.EventServiceInterface;
import com.thws.eventmanager.domain.services.other.TicketPurchaseUseCaseService;
import com.thws.eventmanager.infrastructure.components.persistence.adapter.EventHandler;
import com.thws.eventmanager.infrastructure.components.persistence.entities.EventEntity;
import com.thws.eventmanager.infrastructure.components.persistence.entities.TicketEntity;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.EventMapper;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.TicketMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class EventService implements EventServiceInterface {
    private final EventMapper eventMapper = new EventMapper();
    private static final Logger logger = LoggerFactory.getLogger(EventService.class);
    TicketService ticketService = new TicketService();
    TicketMapper ticketMapper = new TicketMapper();
    TicketPurchaseUseCaseService ticketPurchaseUseCaseService = new TicketPurchaseUseCaseService();
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
    public Optional<EventEntity> deleteEvent(Event event) {
        try(EventHandler eventHandler = new EventHandler()) {
            return eventHandler.deleteById(event.getId());
        } catch (Exception e) {
            throw new InvalidEventException("Failed to delete event.");
        }
    }

    @Override
    public Optional<EventEntity> getEventById(long id) {
        try(EventHandler eventHandler = new EventHandler()) {
            return eventHandler.findById(id);
        } catch (Exception e) {
            throw new InvalidEventException("Failed to get event.");
        }
    }

    @Override
    public List<EventEntity> getAllEvents(List<String> criteria, List<Object> values) {
        logger.info("Criteria: " + criteria);
        logger.info("Values: " + values);

        try (EventHandler eventHandler = new EventHandler()) {
            if (criteria.size() != values.size()) {
                throw new InvalidEventException("Criteria and values lists must have the same size.");
            } else if (criteria.isEmpty()) {
                return eventHandler.findAll();
            } else {
                return eventHandler.searchByCriteria(criteria, values);
            }
        } catch (Exception e) {
            throw new InvalidEventException("Failed to get filtered addresses from database.");
        }
    }

    @Override
    public List<EventEntity> getAllEvents() {
        try(EventHandler eventHandler = new EventHandler()) {
            return eventHandler.findAll();
        } catch (Exception e) {
            throw new InvalidEventException("Failed to get all events from database.");
        }
    }

    @Override
    public boolean isBlocked(Event event, User user) {
        return event.getBlockList().contains(user);
    }

    @Override
    public boolean isArtist(Event event, User user) {
        return event.getArtists().contains(user);
    }

    @Override
    public EventEntity blockUser(Event inputevent, User user) {
        try(EventHandler eventHandler = new EventHandler()) {
            Event event = eventMapper.toModel(eventHandler.findById(inputevent.getId()).orElseThrow());
            if (isArtist(event, user)) {
                throw new InvalidEventException("Artists cannot be blocked.");
            } else if (isBlocked(event, user)) {
                throw new InvalidEventException("User is already blocked.");
            }
            event.getBlockList().add(user);
            return eventHandler.save(eventMapper.toEntity(event));
        } catch (Exception e) {
            throw new InvalidEventException("Failed to block user.");
        }
    }

    @Override
    public boolean refundEvent(Event event) {
        List<TicketEntity> ticketEntityList = ticketService.getAllTickets(List.of("event_id"), List.of(event.getId()));
        List<Ticket> ticketList = ticketEntityList.stream().map(ticketMapper::toModel).toList();
        for (Ticket ticket : ticketList) {
            ticketPurchaseUseCaseService.refundTicket(ticket);
        }
        return true;
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