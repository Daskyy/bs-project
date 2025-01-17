package com.thws.eventmanager.database;

import com.thws.eventmanager.domain.models.*;
import com.thws.eventmanager.infrastructure.components.persistence.EventHandler;

public class EventHandlerTest {
    public static void main(String[] args) {
        // Initialize the database handler
        EventHandler EventHandler = new EventHandler();

        try {
            EventLocation eventLocation = new EventLocation(
                    new Address("Stadt", "Deutschland", 4, "Strasse", 12345),
                    100,
                    "Location"
            );

/*            for (int i = 0; i < 10; i++) {
                EventHandler.saveEvent(new Event(
                        "Event " + i,
                        "Description " + i,
                        20,
                        0,
                        1,
                        List.of(new User("Artist " + i, "artist" + i + "@example.com", "password", Permission.ARTIST)),
                        eventLocation
                ));
            }*/

            EventHandler.getAllEvents().forEach(event -> System.out.println(
                    "ID: " + event.getId() +
                            ", Name: " + event.getName() +
                            ", Description: " + event.getDescription() +
                            ", Location: " + event.getEventLocation().getName() +
                            ", Address: " + event.getEventLocation().getAddress().getStreet()
            ));

        } finally {
            // Close the database connection
            EventHandler.close();
        }
    }
}
