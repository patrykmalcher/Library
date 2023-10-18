package pl.edu.mimuw.servlets.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServlet;
import pl.edu.mimuw.library.Library;

import java.util.List;
import java.util.Map;

public abstract class LibraryServlet {
    protected static Library library;

    public LibraryServlet() {
        library = new Library();
    }

    public abstract String doGet(Map<String, List<String>> params) throws JsonProcessingException;
    public abstract String doPost(Map<String, List<String>> params) throws JsonProcessingException;
}
