package pl.edu.mimuw.servlets;

import com.fasterxml.jackson.core.JsonProcessingException;
import pl.edu.mimuw.servlets.interfaces.LibraryServlet;

import java.util.List;
import java.util.Map;

public class AddBookServlet extends LibraryServlet {
    @Override
    public String doGet(Map<String, List<String>> params) throws JsonProcessingException {
        return null;
    }

    @Override
    public String doPost(Map<String, List<String>> params) throws JsonProcessingException {
        final int book = Integer.parseInt(params.get("book").get(0));
        final String detail = params.get("detail").get(0);
        final var check = library.addBook(book, detail);

        return check ? "Book has been added." : "Book already exists.";
    }
}
