package pl.edu.mimuw.servlets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.edu.mimuw.servlets.interfaces.LibraryServlet;

import java.util.List;
import java.util.Map;

public class GetBooksServlet extends LibraryServlet {

    @Override
    public String doGet(Map<String, List<String>> params) throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(library.getBooks()); // TODO AS BYTES
    }

    @Override
    public String doPost(Map<String, List<String>> params) throws JsonProcessingException {
        return null;
    }
}
