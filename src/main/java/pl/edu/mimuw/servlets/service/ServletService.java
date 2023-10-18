package pl.edu.mimuw.servlets.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import pl.edu.mimuw.http.HttpRequest;
import pl.edu.mimuw.http.HttpResponse;
import pl.edu.mimuw.servlets.*;
import pl.edu.mimuw.servlets.interfaces.LibraryServlet;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ServletService {
    private Map<String, LibraryServlet> servlets;

    public ServletService() {
        servlets = new HashMap<String, LibraryServlet>();
        servlets.put("/add", new AddBookServlet());
        servlets.put("/delete", new DeleteBookServlet());
        servlets.put("/edit", new EditBookServlet());
        servlets.put("/books", new GetBooksServlet());
        servlets.put("/book", new ShowBookServlet());
    }

    public HttpResponse handle(HttpRequest request) throws JsonProcessingException {
        LibraryServlet libraryServlet = servlets.get(request.getEndpoint());

        String res;
        if (Objects.equals(request.getType(), "GET"))
            res = libraryServlet.doGet(request.getParams());
        else {
            assert (Objects.equals(request.getType(), "POST"));
            res = libraryServlet.doPost(request.getParams());
        }

        return new HttpResponse(res);
    }
}
