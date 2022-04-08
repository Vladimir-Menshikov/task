package com.example.demo.controller;

import com.example.demo.models.Book;
import com.example.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
@Validated
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
            books = bookService.getAllDetail();
        }

        return books;
    }

    @GetMapping("/book")
    public List<Book> getAllSummary() {
        return bookService.getAllSummary();
    }

    @GetMapping("/book/{id}")
    public Book getBook(@PathVariable("id") @Pattern(regexp = "^[0-9]+$") String id) {
        return bookService.getByIdSummary(Long.valueOf(id));
    }

    @GetMapping("/book/{id}/detail")
    public Book getBookDetail(@PathVariable("id") @Pattern(regexp = "^[0-9]+$") String id) {
        return bookService.getByIdDetail(Long.valueOf(id));
    }

    @PostMapping
    public Book add(@RequestBody final Book book) {
       return bookService.saveBook(book);
    }

    @PutMapping
    public Book update(@RequestBody Book book) {
        return bookService.updateBook(book);
    }

    @DeleteMapping("/book/{id}")
    public String delete(@PathVariable("id") @Pattern(regexp = "^[0-9]+$") String id) {
        bookService.deleteBook(Long.valueOf(id));
        return "book deleted successfully";
    }
}
