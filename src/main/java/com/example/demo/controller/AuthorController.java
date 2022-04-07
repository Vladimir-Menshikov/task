package com.example.demo.controller;

import com.example.demo.models.Author;
import com.example.demo.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public List<Author> main() {
        return authorService.getAllDetail();
    }

    @GetMapping("/summary")
    public List<Author> getAllSummary() {
        return authorService.getAllSummary();
    }

    @GetMapping("/{id}/detail")
    public Author getByIdDetail(@PathVariable("id") Long id) {
        return authorService.getByIdDetail(id);
    }

    @GetMapping("/{id}")
    public Author getByIdSummary(@PathVariable("id") Long id) {
        return authorService.getByIdSummary(id);
    }

    @PostMapping
    public Author add(@RequestBody final Author author) {
        return authorService.save(author);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        authorService.deleteById(id);
        return "author deleted successfully";
    }
    @PutMapping
    public Author update(@RequestBody Author author) {
        return authorService.update(author);
    }
}
