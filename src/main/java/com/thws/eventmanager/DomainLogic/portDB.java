package com.thws.eventmanager.DomainLogic;

import com.thws.eventmanager.DomainLogic.Entities.Permission;
import com.thws.eventmanager.DomainLogic.Entities.UserEntity;

public interface portDB {

    void addUser(UserEntity user); // Kann weg, wenn man keine Neuen User anlegen will
    void setUserPermission(String email, Permission level);
    UserEntity findByEmail(String email);
    boolean ticketsAvailable(long eventId);
    void createReservation(String email, long eventId, int amount);
    void createReservationOnWaitlist(String email, long eventId, int amount);
    void deleteReservation(String email, long eventId);

}
