package pl.edu.mimuw.library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest
{
    private Library library;

    @BeforeEach
    public void setup()
    {
        library = new Library();

        final var check = library.addBook(1, "Test");
        assertTrue(check);
        assertEquals(library.getBooks().size(), 1);
    }

    @Test
    public void shouldNotAddBookWhenItAlreadyExists()
    {
        final var check = library.addBook(1, "Test");
        assertFalse(check);
        assertEquals(library.getBooks().size(), 1);
    }

    @Test
    public void shouldNotDeleteWhenTheBookDoesntExist()
    {
        var check = library.deleteBook(1);
        assertTrue(check);
        assertEquals(library.getBooks().size(), 0);

        check = library.deleteBook(1);
        assertFalse(check);
        assertEquals(library.getBooks().size(), 0);
    }

    @Test
    public void shouldNotEditWhenBookDoesntExist()
    {
        var check = library.editBook(1, "Test2");
        assertTrue(check);
        assertEquals(library.getDetail(1), "Test2");

        check = library.deleteBook(1);
        assertTrue(check);
        assertEquals(library.getBooks().size(), 0);

        check = library.editBook(1, "Test2");
        assertFalse(check);
    }
}