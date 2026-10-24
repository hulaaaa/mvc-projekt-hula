package pl.merito.kino.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/filmy/add")
public class FilmFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        renderForm(req, resp, new ArrayList<>(), "", "", "");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String tytul = trim(req.getParameter("tytul"));
        String rezyser = trim(req.getParameter("rezyser"));
        String rok = trim(req.getParameter("rok"));

        List<String> bledy = new ArrayList<>();
        if (tytul.isEmpty()) {
            bledy.add("Tytuł jest wymagany.");
        }
        if (rezyser.isEmpty()) {
            bledy.add("Reżyser jest wymagany.");
        }
        if (rok.isEmpty()) {
            bledy.add("Rok produkcji jest wymagany.");
        } else {
            try {
                int y = Integer.parseInt(rok);
                int teraz = Year.now().getValue();
                if (y < 1895 || y > teraz) {
                    bledy.add("Rok produkcji musi być z zakresu 1895–" + teraz + ".");
                }
            } catch (NumberFormatException e) {
                bledy.add("Rok produkcji musi być liczbą.");
            }
        }

        if (!bledy.isEmpty()) {
            renderForm(req, resp, bledy, tytul, rezyser, rok);
            return;
        }

        String encoded = URLEncoder.encode(tytul, StandardCharsets.UTF_8);
        resp.sendRedirect(req.getContextPath() + "/filmy/dodany?tytul=" + encoded);
    }

    private String trim(String value) {
        return value == null ? "" : value.trim();
    }

    private void renderForm(HttpServletRequest req, HttpServletResponse resp, List<String> bledy,
                            String tytul, String rezyser, String rok) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html><html lang='pl'><head><meta charset='UTF-8'><title>Dodaj film</title></head><body>");
        out.println("<h1>Dodaj film</h1>");
        for (String blad : bledy) {
            out.println("<p style='color:red'>" + escape(blad) + "</p>");
        }
        out.println("<form method='post'>");
        out.println("<p>Tytuł: <input name='tytul' value='" + escape(tytul) + "'></p>");
        out.println("<p>Reżyser: <input name='rezyser' value='" + escape(rezyser) + "'></p>");
        out.println("<p>Rok produkcji: <input name='rok' value='" + escape(rok) + "'></p>");
        out.println("<button type='submit'>Zapisz</button>");
        out.println("</form>");
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
