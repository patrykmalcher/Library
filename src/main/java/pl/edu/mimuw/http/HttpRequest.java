package pl.edu.mimuw.http;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
public class HttpRequest {
    private String endpoint;
    private String type;
    private Map<String, List<String>> params;
}
