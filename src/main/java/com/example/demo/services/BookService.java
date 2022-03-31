package com.example.demo.services;

import com.example.demo.exceptions.BookNotFoundException;
import com.example.demo.models.Book;
import com.example.demo.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> list() {
        List<Book> books = bookRepository.findAll();
        return bookRepository.findAll();
    }

    public boolean saveBook(Book book) {
        bookRepository.save(book);
        return true;
    }


    public boolean deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
        return true;
    }

    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
    }

    public boolean updateBook(Long bookId) {
        Book bookFromDb = getBookById(bookId);
        bookFromDb.setName("Default");
        bookRepository.save(bookFromDb);
        return true;
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
