package com.example.demo.services;

import com.example.demo.exceptions.BookNotFoundException;
import com.example.demo.exceptions.NoSuchAuthorException;
import com.example.demo.exceptions.NonUniqueIsbnException;
import com.example.demo.models.Book;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.repositories.BookRepository;
import org.hibernate.NonUniqueObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public List<Book> getAllDetail() {
        return bookRepository.findAllDetail();
    }

    public List<Book> getAllSummary() {
        return bookRepository.findAllSummary();
    }

    public Book getByIdSummary(Long bookId) {
        Book book = bookRepository.findByIdSummary(bookId);
        if (book == null) {
            throw new BookNotFoundException();
        }
        return book;
    }

    public Book getByIdDetail(Long bookId) {
        Book book = bookRepository.findByIdDetail(bookId);
        if (book == null) {
            throw new BookNotFoundException();
        }
        return book;
    }

    public Book saveBook(Book book) {
        validateBook(book);
        return  bookRepository.save(book);
    }

    public void deleteBook(Long bookId) {
        if(bookRepository.findById(bookId).isEmpty()) {
            throw new BookNotFoundException();
        }
        bookRepository.deleteById(bookId);
    }

    public Book updateBook(Book book) {
        if(bookRepository.findById(book.getId()).isEmpty()) {
            throw new BookNotFoundException();
        }
        validateBook(book);
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

    private void validateBook(Book book) {
        if(bookRepository.existsByIsbn(book.getIsbn())) {
            throw new NonUniqueIsbnException();
        }
        if(!authorRepository.existsById(book.getAuthor().getId())) {
            throw new NoSuchAuthorException();
        }
    }
}
