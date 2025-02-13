package com.thws.eventmanager.domain.services.db;

import com.thws.eventmanager.domain.models.EventLocation;
import com.thws.eventmanager.domain.port.in.EventLocationServiceInterface;
import com.thws.eventmanager.infrastructure.components.persistence.adapter.EventLocationHandler;
import com.thws.eventmanager.infrastructure.components.persistence.entities.EventLocationEntity;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.EventLocationMapper;

import java.util.List;
import java.util.Optional;

public class EventLocationService implements EventLocationServiceInterface {
    EventLocationMapper eventLocationMapper = new EventLocationMapper();

    @Override
    public EventLocationEntity saveLocation(EventLocation location) {
        try(EventLocationHandler locationHandler = new EventLocationHandler()) {
            return locationHandler.save(eventLocationMapper.toEntity(location));
        } catch (Exception e) {
            throw new RuntimeException("Failed to save location.");
        }
    }

    @Override
    public Optional<EventLocationEntity> deleteLocation(EventLocation location) {
        try(EventLocationHandler locationHandler = new EventLocationHandler()) {
            return locationHandler.deleteById(location.getId());
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete location.");
        }
    }

    @Override
    public Optional<EventLocationEntity> getLocationById(long id) {
        try(EventLocationHandler locationHandler = new EventLocationHandler()) {
            return locationHandler.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get location.");
        }
    }

    @Override
    public List<EventLocationEntity> getAllEventLocations() {
        try(EventLocationHandler locationHandler = new EventLocationHandler()) {
            return locationHandler.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to get all locations.");
        }
    }

    @Override
    public List<EventLocationEntity> getAllEventLocations(List<String> criteria, List<Object> values) {
        try(EventLocationHandler locationHandler = new EventLocationHandler()) {
            if (criteria.size() != values.size()) {
                throw new RuntimeException("Criteria and values lists must have the same size.");
            } else if (criteria.isEmpty()) {
                return locationHandler.findAll();
            } else {
                return locationHandler.searchByCriteria(criteria, values);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to get filtered locations.");
        }
    }
}
