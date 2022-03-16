package com.revature.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.domain.Notes;
import com.revature.server.DatabaseController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AddNoteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    /**
     * This will receive the Json object from AddNotes.js
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Notes note = mapper.readValue(req.getInputStream(), Notes.class);
        new DatabaseController().InsertData(note);
//        resp.sendRedirect("Display.html");
    }
}
