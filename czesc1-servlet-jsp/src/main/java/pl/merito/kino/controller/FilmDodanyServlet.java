package pl.merito.kino.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/filmy/dodany")
public class FilmDodanyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String tytul = req.getParameter("tytul");
        if (tytul == null) {
            tytul = "";
        }
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html><html lang='pl'><head><meta charset='UTF-8'><title>Zapisano film</title></head><body>");
        out.println("<h1>Zapisano film: " + escape(tytul) + "</h1>");
        out.println("<p><a href='" + req.getContextPath() + "/filmy/add'>Dodaj kolejny</a></p>");
        out.println("<p><a href='" + req.getContextPath() + "/index.html'>Powrót</a></p>");
        out.println("</body></html>");
    }

    private static String escape(String s) {
        return s.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}
