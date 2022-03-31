package com.example.demo.controller;

import com.example.demo.models.Book;
import com.example.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> main(@RequestParam(required = false) String name,
                       @RequestParam(required = false)
                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                               LocalDate date) {
        List<Book> books;

        if (date != null && name != null && !name.isEmpty()) {
            books = bookService.findByNameAndDate(name, date);
        }
        else if (name != null && !name.isEmpty()) {
            books = bookService.findByName(name);
        }
        else if (date != null) {
            books = bookService.findByDate(date);
        }
        else {
            books = bookService.list();
        }

        return books;
    }

    @GetMapping("/book/{id}")
    public Book getBookDetails(@PathVariable("id") Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    public Book add(@RequestBody final Book book) {
       return bookService.saveBook(book);
    }

    @PutMapping
    public Book update(@RequestBody Book book) {
        return bookService.updateBook(book);
    }

    @DeleteMapping
    public String delete(@RequestParam(value = "id") Long id, Model model) {
        bookService.deleteBook(id);
        return "book deleted successfully";
    }
}
