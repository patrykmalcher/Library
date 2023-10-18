package pl.edu.mimuw.servlets;

import com.fasterxml.jackson.core.JsonProcessingException;
import pl.edu.mimuw.servlets.interfaces.LibraryServlet;

import java.util.List;
import java.util.Map;

public class ShowBookServlet extends LibraryServlet {
    @Override
    public String doGet(Map<String, List<String>> params) throws JsonProcessingException {
        final int book = Integer.parseInt(params.get("book").get(0));

        return library.getDetail(book);
    }

    @Override
    public String doPost(Map<String, List<String>> params) throws JsonProcessingException {
        return null;
    }
}
