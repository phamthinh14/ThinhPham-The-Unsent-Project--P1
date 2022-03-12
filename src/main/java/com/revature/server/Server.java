package com.revature.server;

import com.revature.servlet.DefaultServlet;
import com.revature.servlet.DisplayNotesServlet;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Server {
    Tomcat server;

    /**
     *
     */
    public Server() {
        server = new Tomcat();
        server.getConnector();
        server.addContext("", null);
        new DatabaseController().BuildDatabase();
        // Default servlet
        server.addServlet("", "defaultServlet", new DefaultServlet()).addMapping("/*");
        server.addServlet("", "displayServlet", new DisplayNotesServlet()).addMapping("/displayNotes");
        server.addServlet("", "addNoteServlet", new DisplayNotesServlet()).addMapping("/addNotes");
    }

    /**
     * Starts server
     */
    public void run() {
        try {
            server.start();
        } catch (LifecycleException e) {
            System.err.println("Failed to start server: " + e.getMessage());
        }
    }
}
