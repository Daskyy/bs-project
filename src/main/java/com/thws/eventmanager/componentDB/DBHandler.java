package com.thws.eventmanager.componentDB;

import com.thws.eventmanager.DomainLogic.Entities.Permission;
import com.thws.eventmanager.DomainLogic.Entities.UserEntity;
import com.thws.eventmanager.DomainLogic.portDB;

public class DBHandler implements portDB {
    @Override
    public void addUser(UserEntity user) {

    }

    @Override
    public void setUserPermission(String email, Permission level) {

    }

    @Override
    public UserEntity findByEmail(String email) {
        return null;
    }

    @Override
    public boolean ticketsAvailable(long eventId) {
        return false;
    }

    @Override
    public void createReservation(String email, long eventId, int amount) {

    }

    @Override
    public void createReservationOnWaitlist(String email, long eventId, int amount) {

    }


    @Override
    public void deleteReservation(String email, long eventId) {
        // deletes Reservation in Event or on Waitlist
    }
}
