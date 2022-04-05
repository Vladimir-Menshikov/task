package com.example.demo.services;

import com.example.demo.exceptions.BookNotFoundException;
import com.example.demo.models.Book;
import com.example.demo.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> list() {
        return bookRepository.findAll();
    }

    public Book saveBook(Book book) {
        return  bookRepository.save(book);
    }

    public void deleteBook(Long bookId) {
        if(bookRepository.findById(bookId).isEmpty()) {
            throw new BookNotFoundException();
        }
        bookRepository.deleteById(bookId);
    }

    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
    }

    public Book updateBook(Book book) {
        if(bookRepository.findById(book.getId()).isEmpty()) {
            throw new BookNotFoundException();
        }
        return bookRepository.save(book);
    }

    public List<Book> findByName(String name) {
        List<Book> books;

        if (name != null && !name.isEmpty()) {
            books = bookRepository.findByName(name);
        } else {
            books = bookRepository.findAll();
        }
        return books;
    }

    public List<Book> findByDate(LocalDate date) {
        List<Book> books;

        if (date != null) {
            books = bookRepository.findByReleaseDate(date);
        } else {
            books = bookRepository.findAll();
        }
        return books;
    }

    public List<Book> findByNameAndDate(String name, LocalDate date) {
        List<Book> books;
        if(date != null && name != null && !name.isEmpty()) {
            books = bookRepository.findByNameAndReleaseDate(name, date);
        } else {
            books = bookRepository.findAll();
        }
        return books;
    }
}
