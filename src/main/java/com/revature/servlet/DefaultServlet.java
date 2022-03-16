package com.revature.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class DefaultServlet extends HttpServlet {
    /**
     * This servlet is to copy and write html. It allows us to use html without servlet printing it out
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filename = req.getPathInfo();
        String resourceDir = "static";
        InputStream file = getClass().getClassLoader().getResourceAsStream(resourceDir + filename);
        String mimeType = getServletContext().getMimeType(filename);
        resp.setContentType(mimeType);
        IOUtils.copy(file, resp.getOutputStream());
    }
}
