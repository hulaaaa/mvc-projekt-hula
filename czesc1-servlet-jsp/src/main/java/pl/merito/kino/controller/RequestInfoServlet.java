package pl.merito.kino.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet("/info")
public class RequestInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html><html lang='pl'><head><meta charset='UTF-8'><title>Informacje o żądaniu</title></head><body>");
        out.println("<h1>Informacje o żądaniu</h1>");
        out.println("<ul>");
        out.println("<li>Metoda: " + escape(req.getMethod()) + "</li>");
        out.println("<li>URI: " + escape(req.getRequestURI()) + "</li>");
        out.println("<li>Kontekst aplikacji: " + escape(req.getContextPath()) + "</li>");
        out.println("<li>Ścieżka servletu: " + escape(req.getServletPath()) + "</li>");
        out.println("<li>Query string: " + escape(String.valueOf(req.getQueryString())) + "</li>");
        out.println("<li>Adres klienta: " + escape(req.getRemoteAddr()) + "</li>");
        out.println("<li>Przeglądarka: " + escape(req.getHeader("User-Agent")) + "</li>");
        req.getParameterMap().forEach((name, values) ->
                out.println("<li>Parametr " + escape(name) + " = " + escape(Arrays.toString(values)) + "</li>"));
        out.println("</ul>");
        out.println("<p><a href='" + req.getContextPath() + "/index.html'>Powrót</a></p>");
        out.println("</body></html>");
    }

    private static String escape(String s) {
        if (s == null) {
            return "";
        }
        return s.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}
