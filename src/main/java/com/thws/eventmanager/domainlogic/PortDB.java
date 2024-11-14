package com.thws.eventmanager.domainlogic;

import com.thws.eventmanager.domainlogic.entities.Permission;
import com.thws.eventmanager.domainlogic.entities.User;

public interface PortDB {

    void addUser(User user); // Kann weg, wenn man keine Neuen User anlegen will
    void setUserPermission(String email, Permission level);
    User findByEmail(String email);
    boolean ticketsAvailable(long eventId);
    void createReservation(String email, long eventId, int amount);
    void createReservationOnWaitlist(String email, long eventId, int amount);
    void deleteReservation(String email, long eventId);

}
