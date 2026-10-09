# Kino

Projekt zaliczeniowy – Programowanie aplikacji internetowych MVC
Autor: Dmytro Hula, nr albumu 164497

## Temat
Aplikacja do obsługi małego kina. Pokazuje repertuar (filmy i seanse) i pozwala zarezerwować miejsca na wybrany seans. Administrator może dodawać filmy i seanse oraz przeglądać rezerwacje.

## Struktura repozytorium
- czesc1-servlet-jsp – aplikacja Servlet + JSP + JSTL (Tomcat 10.1)
- czesc2-spring-boot – aplikacja Spring Boot + Thymeleaf
- docs – dokumentacja projektu

## Uruchomienie części I
Wymagane: JDK 21, Maven, Tomcat 10.1.

```
cd czesc1-servlet-jsp
mvn clean package
```

Plik `target/kino.war` skopiować do `[tomcat]/webapps`, uruchomić Tomcat (`bin/startup.sh` lub `bin\startup.bat`) i wejść na http://localhost:8080/kino/
