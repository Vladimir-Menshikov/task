package com.example.demo.controller;

import com.example.demo.exceptions.BookNotFoundException;
import com.example.demo.models.Book;
import com.example.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public String main(@RequestParam(required = false) String name,
                       @RequestParam(required = false)
                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                               LocalDate date,Model model) {
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

        model.addAttribute("books", books);
        model.addAttribute("date", date);
        model.addAttribute("name", name);
        return "main";
    }

    @GetMapping("/book/{id}")
    public Book getBookDetails(@PathVariable("id") Long id, Model model) {
        Book book = bookService.getBookById(id);
        return book;
    }

    @PostMapping("/add")
    public String add(@RequestParam String name,
                      @RequestParam
                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                              LocalDate releaseDate,
                      @RequestParam BigDecimal price) {
       Book book = new Book(name, releaseDate, price);

       bookService.saveBook(book);

       return "redirect:/";
    }

    @PostMapping("delete")
    public String delete(@RequestParam(value = "id") Long id, Model model) {
        bookService.deleteBook(id);
        return "redirect:/";
    }
}
