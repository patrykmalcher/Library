package pl.edu.mimuw.library;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Library {
    private static Map<Integer, String> books;

    public Library() {
        books = new HashMap<Integer, String>();
    }

    public boolean addBook(final int book, final String detail) {
        if (books.containsKey(book)) {
            return false;
        }
        books.put(book, detail);
        return true;
    }

    public Collection<Integer> getBooks() {
        return books.keySet();
    }

    public Map<Integer, String> getBooksMap() {
        return books;
    }

    public boolean deleteBook(final int book) {
        if (!books.containsKey(book)) {
            return false;
        }
        books.remove(book);
        return true;
    }

    public boolean editBook(final int book, final String detail) {
        if (!books.containsKey(book)) {
            return false;
        }
        books.put(book, detail);
        return true;
    }

    public String getDetail(final int book) {
        return books.get(book);
    }
}