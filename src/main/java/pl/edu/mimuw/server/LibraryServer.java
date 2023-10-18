package pl.edu.mimuw.server;

import pl.edu.mimuw.http.HttpRequest;
import pl.edu.mimuw.http.HttpResponse;
import pl.edu.mimuw.servlets.service.ServletService;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private final ServletService servletService;

    public LibraryServer() {
        servletService = new ServletService();
    }

    public void start(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        try {
            while (true) {
                Socket socket = serverSocket.accept();

                BufferedReader inBufferReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String requestLine = inBufferReader.readLine();

                HttpResponse response = null;

                if (requestLine != null) {
                    String[] requestParts = requestLine.split(" ");
                    if (requestParts.length == 3) {
                        String httpMethod = requestParts[0];
                        String url = requestParts[1];

                        response = servletService.handle(getHttpRequest(url, httpMethod));
                    }
                }

                OutputStream outStream = socket.getOutputStream();
                BufferedReader bufferedReader = new BufferedReader(new StringReader("A Message from server."));

                try {
                    outStream.write("HTTP/1.1 200 OK\r\n".getBytes());
                    outStream.write("Content-Type: application/json\r\n".getBytes());
                    outStream.write("\r\n".getBytes());

                    assert response != null;
                    outStream.write(response.getJacksonData().getBytes());

                    outStream.flush();
                    outStream.close(); // Socket will close automatically once output stream is closed.
                } catch (SocketException e) {
                    // Handle the case where client closed the connection while server was writing to it
                    socket.close();
                }

                bufferedReader.close();
                inBufferReader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private HttpRequest getHttpRequest(String url, String httpMethod) {
        Map<String, List<String>> params = new HashMap<String, List<String>>();
        String[] urlParts = url.split("\\?");
        if (urlParts.length > 1) {
            String query = urlParts[1];
            for (String param : query.split("&")) {
                String[] pair = param.split("=");
                String key = URLDecoder.decode(pair[0], StandardCharsets.UTF_8);
                String value = "";
                if (pair.length > 1) {
                    value = URLDecoder.decode(pair[1], StandardCharsets.UTF_8);
                }

                List<String> values = params.computeIfAbsent(key, k -> new ArrayList<String>());
                values.add(value);
            }
        }
        return new HttpRequest(urlParts[0], httpMethod, params);
    }
}
