package com.thws.eventmanager.infrastructure.GraphQL;
import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.ee10.servlet.ServletHolder;
import org.eclipse.jetty.server.Server;

public class ServerGQL {

        public static void main(String[] args) throws Exception {
            Server server = new Server(8080);

            ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
            context.setContextPath("/");
            server.setHandler(context);

            // Dein GraphQL-Servlet
            context.addServlet(new ServletHolder(new Servlet()), "/graphql");

            server.start();
            System.out.println("Jetty started on port 8080 -> http://localhost:8080/graphql");

            server.join();
        }

}
