package com.thws.eventmanager.componentdb;

import com.thws.eventmanager.domain.entities.Permission;
import com.thws.eventmanager.domain.entities.User;
import com.thws.eventmanager.domain.PortDB;

public class DBHandler implements PortDB {
    @Override
    public void addUser(User user) {

    }

    @Override
    public void setUserPermission(String email, Permission level) {

    }

    @Override
    public User findByEmail(String email) {
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
