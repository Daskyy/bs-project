package com.thws.eventmanager.domain;

import com.thws.eventmanager.domain.models.Permission;
import com.thws.eventmanager.domain.models.User;

public interface PortDB {

    void addUser(User user); // Kann weg, wenn man keine Neuen User anlegen will
    void setUserPermission(String email, Permission level);
    User findByEmail(String email);
    boolean ticketsAvailable(long eventId);
    void createReservation(String email, long eventId, int amount);
    void createReservationOnWaitlist(String email, long eventId, int amount);
    void deleteReservation(String email, long eventId);

}
