package com.thws.eventmanager.infrastructure.api;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.ee10.servlet.ServletHolder;
import com.thws.eventmanager.infrastructure.GraphQL.Servlet;

public class GraphQLServer {
    public static void main(String[] args) throws Exception {
        int port = 8081;
        if(args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        Server server = new Server(port);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        // Registriere das GraphQL‑Servlet
        context.addServlet(new ServletHolder(new Servlet()), "/graphql");
        server.setHandler(context);
        server.start();
        // server.join() blockiert – daher nur server.start() nutzen
        System.out.println("GraphQL Server started on port " + port);
    }
} 