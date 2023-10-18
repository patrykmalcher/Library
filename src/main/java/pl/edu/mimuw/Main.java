package pl.edu.mimuw;

import pl.edu.mimuw.server.LibraryServer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        LibraryServer server = new LibraryServer();
        server.start(8080);
    }
}