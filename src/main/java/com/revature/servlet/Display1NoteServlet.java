package com.revature.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.domain.Notes;
import com.revature.server.DatabaseController;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.List;

public class Display1NoteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("searchName");
        List<Notes> notesList = new DatabaseController().SearchByName(name);
        notesList.forEach(System.out::println);
//        resp.setContentType("html");
        resp.getWriter().println(notesList.get(0));
        ObjectMapper mapper = new ObjectMapper();
        String results = mapper.writeValueAsString(notesList);
        resp.setContentType("application/json");
        resp.getWriter().println(results);
//        RequestDispatcher view = req.getRequestDispatcher("static/SearchName.html");
//        // don't add your web-app name to the path
//
//        view.forward(req, resp);
    }



}
