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
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
                "    <link rel=\"stylesheet\" href=\"SearchName.css\">\n" +
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
                "            <button id=\"home-button\" onclick=\"enableBtt()\">Home Page</button>\n" +
                "        </div>\n" +
                "        <div class=\"item3\">\n" +
                "            <button id=\"search-button\" onclick=\"searchBtt()\">Search</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</nav>\n" +
                "<br><br>\n" +
                "<div class=\"displayNotes\" id=\"displayDiv\">\n" +
                "    <div class=\"card-columns\">\n" +
                "\n";

        //language=HTML
        String footer = "</div>\n" +
                "</div>\n" +
                "<script src=\"SearchName.js\"></script>\n" +
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
        String content = "";
        if (!notesList.isEmpty()) {
            for (int i = 0; i < notesList.size(); i++) {
                String encodedMess = URLEncoder.encode(notesList.get(i).getMessages(), StandardCharsets.UTF_8.toString());
                //language=HTML
                String temp = "<div class=\"card w-auto h-auto\">\n" +
                        "    <div class=\"flip-card\">\n" +
                        "        <div class=\"flip-card-inner\">\n" +
                        "            <div class=\"flip-card-front\">\n" +
                        "                <img src=\"https://api.qrserver.com/v1/create-qr-code/?data=" + encodedMess +
                        "&size=200x200\" alt=\"Avatar\" style=\"width:200px;height:200px;\" />\n" +
                        "            </div>\n" +
                        "            <div class=\"flip-card-back\">\n" +
                        "                <h1>To " + notesList.get(i).getReceiverName() + "</h1>\n" +
                        "                <p>" + notesList.get(i).getMessages() + "</p>\n" +
                        "                <p>From " + notesList.get(i).getSenderName() + "</p>\n" +
                        "            </div>\n" +
                        "        </div>\n" +
                        "    </div>\n" +
                        "</div>";
                content += temp;
            }
        }
        resp.getWriter().println(head + body + content + footer);
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
