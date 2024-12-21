package com.thws.eventmanager.domain;
import com.thws.eventmanager.domain.models.Address;
import com.thws.eventmanager.domain.models.EventLocation;
import com.thws.eventmanager.domain.models.User;

public interface EventServiceInterface {
    public boolean createEvent(User artist, EventLocation location);

}
