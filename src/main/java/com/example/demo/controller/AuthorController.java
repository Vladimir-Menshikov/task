package com.example.demo.controller;

import com.example.demo.models.Author;
import com.example.demo.models.Book;
import com.example.demo.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public List<Author> main() {
        return authorService.getAll();
    }

    @GetMapping("/{id}")
    public Author getAuthorDetails(@PathVariable("id") Long id) {
        return authorService.getById(id);
    }

    @GetMapping("/{id}/books")
    public List<Book> getBooksByAuthor(@PathVariable("id") Long id) {
        return authorService.getById(id).getBooks();
    }

    @PostMapping
    public Author add(@RequestBody final Author author) {
        return authorService.save(author);
    }

    @PutMapping
    public Author update(@RequestBody Author author) {
        return authorService.update(author);
    }

    @DeleteMapping
    public String delete(@RequestParam(value = "id") Long id) {
        authorService.deleteById(id);
        return "book deleted successfully";
    }
}
