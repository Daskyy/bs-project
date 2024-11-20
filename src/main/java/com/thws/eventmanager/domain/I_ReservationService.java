package com.thws.eventmanager.domain;

public interface I_ReservationService {
    public boolean eventAvailable(long eventId);
    public boolean ticketAvailable(long eventId);
    public void addReservation(String userEmail, long eventId, int amount);
    public void addWaitlistReservation(String userEmail, long eventId, int amount);
    public void cancelReservation(String userEmail, long eventId);
}
