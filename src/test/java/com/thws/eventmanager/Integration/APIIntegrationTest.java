package com.thws.eventmanager.Integration;

import com.thws.eventmanager.Integration.Operations.*;
import com.thws.eventmanager.infrastructure.GraphQL.Models.*;
import com.thws.eventmanager.infrastructure.GraphQL.InputModels.UserInput;
import com.thws.eventmanager.infrastructure.GraphQL.InputModels.AddressInput;
import com.thws.eventmanager.infrastructure.GraphQL.InputModels.EventInput;
import com.github.javafaker.Faker;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.junit.jupiter.api.*;

import java.net.Socket;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class APIIntegrationTest {
    
    private static Server server;
    private static Thread serverThread;
    private static final int PORT = 8081;
    private static final Faker faker = new Faker();
    // Persistente Testdaten, um in späteren Tests verwendet zu werden
    private static UserGQL persistentUser;
    private static AddressGQL persistentAddress;

    @BeforeAll
    public static void startServer() throws Exception {
        serverThread = new Thread(() -> {
            try {
                server = new Server(PORT);
                ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
                context.setContextPath("/");
                // Verwende das GraphQL-Servlet
                context.addServlet(new ServletHolder(new com.thws.eventmanager.infrastructure.GraphQL.Servlet()), "/graphql");
                server.setHandler(context);
                server.start();
                // Entferne den blockierenden server.join() Aufruf, damit der Test weiterläuft
            } catch(Exception e) {
                throw new RuntimeException("Server start failed: " + e.getMessage(), e);
            }
        });
        serverThread.start();
        waitForServer(PORT);

        // Erstelle persistente Testdaten
        UserOperation userOp = new UserOperation();
        UserInput userInput = new UserInput();
        userInput.setName(faker.name().fullName());
        userInput.setEmail(faker.internet().emailAddress());
        userInput.setPassword(faker.internet().password());
        userInput.setPermission(PermissionGQL.CUSTOMER);
        persistentUser = userOp.createUser(userInput);

        AddressOperation addressOp = new AddressOperation();
        AddressInput addressInput = new AddressInput();
        addressInput.setStreet(faker.address().streetName());
        addressInput.setNo(faker.number().numberBetween(1, 100));
        addressInput.setCity(faker.address().city());
        addressInput.setZipCode(faker.number().numberBetween(10000, 99999));
        addressInput.setCountry(faker.address().country());
        persistentAddress = addressOp.createAddress(addressInput);
    }

    private static void waitForServer(int port) throws Exception {
        int retries = 10;
        while (retries-- > 0) {
            try (Socket socket = new Socket("localhost", port)) {
                return;
            } catch (Exception e) {
                Thread.sleep(1000);
            }
        }
        throw new Exception("Server did not start in time");
    }

    @Test
    @Order(1)
    void testUserLifecycle() throws Exception {
        UserOperation userOp = new UserOperation();
        // Erstelle einen neuen Nutzer zur Prüfung (nicht persistent)
        UserInput input = new UserInput();
        input.setName(faker.name().fullName());
        input.setEmail(faker.internet().emailAddress());
        input.setPassword(faker.internet().password());
        input.setPermission(PermissionGQL.CUSTOMER);

        UserGQL createdUser = userOp.createUser(input);
        Assertions.assertNotNull(createdUser.getId());

        input.setName(faker.name().fullName());
        UserGQL updatedUser = userOp.updateUser(createdUser.getId(), input);
        Assertions.assertEquals(input.getName(), updatedUser.getName());

        // Nicht löschen, da persistentUser für weitere Tests genutzt wird
    }
    
    @Test
    @Order(2)
    void testAddressLifecycle() throws Exception {
        AddressOperation addressOp = new AddressOperation();
        AddressInput input = new AddressInput();
        input.setStreet(faker.address().streetName());
        input.setNo(faker.number().numberBetween(1, 100));
        input.setCity(faker.address().city());
        input.setZipCode(faker.number().numberBetween(10000, 99999));
        input.setCountry(faker.address().country());

        AddressGQL addr = addressOp.createAddress(input);
        Assertions.assertNotNull(addr.getId());

        input.setStreet(faker.address().streetName());
        AddressGQL updatedAddress = addressOp.updateAddress(addr.getId(), input);
        Assertions.assertEquals(input.getStreet(), updatedAddress.getStreet());

        AddressGQL deletedAddress = addressOp.deleteAddress(addr.getId());
        Assertions.assertNotNull(deletedAddress);
    }

    @Test
    @Order(3)
    void testEventLifecycle() throws Exception {
         // Verwende persistentUser als Künstler; EventLocation-ID "1" muss existieren
         String eventLocationId = "1";
         EventInput eventInput = new EventInput();
         eventInput.setName(faker.funnyName().name());
         eventInput.setDescription(faker.lorem().sentence());
         eventInput.setStartDate("2031-02-20T10:00:00");
         eventInput.setEndDate("2041-02-20T10:00:00");
         eventInput.setTicketCount(faker.number().numberBetween(50, 1000));
         eventInput.setTicketsSold(0);
         eventInput.setMaxTicketsPerUser(faker.number().numberBetween(1, 10));
         eventInput.setArtists(java.util.List.of(persistentUser.getId()));
         eventInput.setLocation(eventLocationId);
         eventInput.setBlockList(java.util.Collections.emptyList());
         eventInput.setTicketPrice(faker.number().numberBetween(20, 200));
         
         EventOperation eventOp = new EventOperation();
         EventGQL createdEvent = eventOp.createEvent(eventInput);
         Assertions.assertNotNull(createdEvent.getId());
         
         eventInput.setName(faker.funnyName().name());
         EventGQL updatedEvent = eventOp.updateEvent(createdEvent.getId(), eventInput);
         Assertions.assertEquals(eventInput.getName(), updatedEvent.getName());
         
         EventGQL blockedEvent = eventOp.blockUser(createdEvent.getId(), persistentUser.getId());
         Assertions.assertTrue(blockedEvent.getBlockList().stream()
                 .anyMatch(user -> user.getId().equals(persistentUser.getId())));
         
         EventGQL queriedEvent = eventOp.getEvent(createdEvent.getId());
         Assertions.assertNotNull(queriedEvent);
         
         EventGQL deletedEvent = eventOp.deleteEvent(createdEvent.getId());
         Assertions.assertNotNull(deletedEvent);
    }
    
    @Test
    @Order(4)
    void testTicketOperations() throws Exception {
         String eventLocationId = "1";
         EventInput eventInput = new EventInput();
         eventInput.setName(faker.funnyName().name());
         eventInput.setDescription(faker.lorem().sentence());
         eventInput.setStartDate("2031-02-20T10:00:00");
         eventInput.setEndDate("2031-02-20T12:00:00");
         eventInput.setTicketCount(100);
         eventInput.setTicketsSold(0);
         eventInput.setMaxTicketsPerUser(5);
         eventInput.setArtists(java.util.List.of(persistentUser.getId()));
         eventInput.setLocation(eventLocationId);
         eventInput.setBlockList(java.util.Collections.emptyList());
         eventInput.setTicketPrice(50);
         
         EventOperation eventOp = new EventOperation();
         EventGQL eventCreated = eventOp.createEvent(eventInput);
         Assertions.assertNotNull(eventCreated.getId());
         
         TicketOperation ticketOp = new TicketOperation();
         TicketGQL purchasedTicket = ticketOp.purchaseTicket(persistentUser.getId(), eventCreated.getId(), 2, "pm_card_visa", "voucher123");
         Assertions.assertNotNull(purchasedTicket.getId());
         
         PaymentGQL refund = ticketOp.refundTicket(purchasedTicket.getId());
         Assertions.assertNotNull(refund);
         
         EventGQL deletedEvent = eventOp.deleteEvent(eventCreated.getId());
         Assertions.assertNotNull(deletedEvent);
    }
    
    @Test
    @Order(5)
    void testQueryOperations() throws Exception {
         UserOperation userOp = new UserOperation();
         UserGQL queriedUser = userOp.queryUser(persistentUser.getId());
         Assertions.assertNotNull(queriedUser);
         
         UserGQL[] users = userOp.queryUsers();
         Assertions.assertTrue(users.length > 0);
         
         AddressOperation addressOp = new AddressOperation();
         AddressInput addrInput = new AddressInput();
         addrInput.setStreet(faker.address().streetName());
         addrInput.setNo(faker.number().numberBetween(1, 100));
         addrInput.setCity(faker.address().city());
         addrInput.setZipCode(faker.number().numberBetween(10000, 99999));
         addrInput.setCountry(faker.address().country());
         AddressGQL createdAddr = addressOp.createAddress(addrInput);
         Assertions.assertNotNull(createdAddr.getId());
         
         AddressGQL queriedAddr = addressOp.getAddress(createdAddr.getId());
         Assertions.assertNotNull(queriedAddr);
         
         AddressGQL[] addresses = addressOp.getAddresses();
         Assertions.assertTrue(addresses.length > 0);
         
         EventLocationOperation elo = new EventLocationOperation();
         EventLocationGQL eventLoc = elo.getEventLocation("1");
         // Optional: Assertions.assertNotNull(eventLoc);
         
         EventLocationGQL[] eventLocations = elo.getEventLocations();
         // Optional: Assertions.assertTrue(eventLocations.length > 0);
    }
    
    @AfterAll
    public static void tearDown() throws Exception {
         if (server != null) {
             server.stop();
         }
         if (serverThread != null) {
             serverThread.interrupt();
         }
    }
} 