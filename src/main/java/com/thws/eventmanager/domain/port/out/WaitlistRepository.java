package com.thws.eventmanager.domain.port.out;

import com.thws.eventmanager.domain.models.Waitlist;

public interface WaitlistRepository {
    Waitlist getWaitlistByEventId(String eventId);
    void saveWaitlist(Waitlist waitlist);
    void deleteWaitlistFromEvent(String eventId);
}
