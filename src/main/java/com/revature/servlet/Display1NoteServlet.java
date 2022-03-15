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

        //language=HTML
        String head = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Unsent Project</title>\n" +
                "    <link rel=\"stylesheet\" href=\"Display.css\">\n" +
                "    <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\"\n" +
                "          integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">\n" +
                "</head>";
        //language=HTML
        String body = "<body>\n" +
                "<nav>\n" +
                "    <div class=\"grid-container\">\n" +
                "        <div class=\"item1\">\n" +
                "            <a class=\"nameLink\" id=\"nameLink\" href=\"http://localhost:8080/Display.html\">The Unsent Project</a>\n" +
                "        </div>\n" +
                "        <div class=\"item2\">\n" +
                "            <button id=\"home-button\" onclick=\"enableBtt()\">Home</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</nav>\n" +
                "<br><br>\n" +
                "\n" +
                "<script src=\"https://code.jquery.com/jquery-3.2.1.slim.min.js\"\n" +
                "        integrity=\"sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN\"\n" +
                "        crossorigin=\"anonymous\"></script>\n" +
                "<script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js\"\n" +
                "        integrity=\"sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q\"\n" +
                "        crossorigin=\"anonymous\"></script>\n" +
                "<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js\"\n" +
                "        integrity=\"sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl\"\n" +
                "        crossorigin=\"anonymous\"></script>\n" +
                "</body>\n" +
                "\n" +
                "</html>";

        resp.getWriter().println(head + notesList + body);
//        ObjectMapper mapper = new ObjectMapper();
//        String results = mapper.writeValueAsString(notesList);
//        resp.setContentType("application/json");
//        resp.getWriter().println(results);
//        RequestDispatcher view = req.getRequestDispatcher("static/SearchName.html");
//        // don't add your web-app name to the path
//
//        view.forward(req, resp);
    }


}
