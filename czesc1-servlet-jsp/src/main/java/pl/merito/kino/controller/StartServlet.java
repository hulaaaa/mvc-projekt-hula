package pl.merito.kino.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/start")
public class StartServlet extends HttpServlet {

    private static final String[] FILMY = {"Diuna: Część druga", "Oppenheimer", "Seksmisja"};

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html><html lang='pl'><head><meta charset='UTF-8'><title>Kino</title></head><body>");
        out.println("<h1>Repertuar na dziś</h1>");
        out.println("<ul>");
        for (String film : FILMY) {
            out.println("<li>" + film + "</li>");
        }
        out.println("</ul>");
        out.println("<p>Odpowiedź na żądanie GET.</p>");
        out.println("<a href='index.html'>Powrót</a>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String imie = req.getParameter("imie");
        String film = req.getParameter("film");

        if (imie == null || imie.isBlank()) {
            imie = "nieznajomy";
        }

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html><html lang='pl'><head><meta charset='UTF-8'><title>Kino</title></head><body>");
        out.println("<h1>Cześć, " + escape(imie) + "!</h1>");
        if (film != null && !film.isBlank()) {
            out.println("<p>Wybrany film: " + escape(film) + "</p>");
        }
        out.println("<p>Odpowiedź na żądanie POST.</p>");
        out.println("<a href='index.html'>Powrót</a>");
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
