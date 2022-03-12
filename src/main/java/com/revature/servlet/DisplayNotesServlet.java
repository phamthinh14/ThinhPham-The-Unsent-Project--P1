package com.revature.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.domain.Notes;
import com.revature.server.DatabaseController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class DisplayNotesServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DatabaseController controller = new DatabaseController();
        List<Notes> notesList = controller.GetAllData();
        notesList.forEach(System.out::println);

        ObjectMapper mapper = new ObjectMapper();
        String results = mapper.writeValueAsString(notesList);
        resp.setContentType("application/json");
        resp.getWriter().println(results);
    }
}
