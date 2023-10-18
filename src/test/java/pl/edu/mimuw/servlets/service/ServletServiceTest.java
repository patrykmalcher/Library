package pl.edu.mimuw.servlets.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.mimuw.http.HttpRequest;
import pl.edu.mimuw.http.HttpResponse;
import pl.edu.mimuw.servlets.service.ServletService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServletServiceTest {
    private ServletService servletService;
    @BeforeEach
    public void setup() {
        servletService = new ServletService();
    }

    @Test
    public void addBookServletTest() throws JsonProcessingException {
        Map<String, List<String>> params = new HashMap<String, List<String>>();
        params.put("book", new ArrayList<String>() {{
            add("1");
        }});
        params.put("detail", new ArrayList<String>() {{
            add("First book");
        }});

        HttpRequest httpRequest = new HttpRequest("/add", "POST", params);
        final HttpResponse httpResponse = servletService.handle(httpRequest);
        assertEquals(httpResponse.getJacksonData(), "Book has been added.");

        final HttpResponse httpResponse2 = servletService.handle(httpRequest);
        assertEquals(httpResponse2.getJacksonData(), "Book already exists.");
    }
}
