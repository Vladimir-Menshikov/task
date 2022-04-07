package com.example.demo.services;

import com.example.demo.exceptions.AuthorNotFoundException;
import com.example.demo.exceptions.BookNotFoundException;
import com.example.demo.models.Author;
import com.example.demo.models.Book;
import com.example.demo.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAllDetail() {
        return authorRepository.findAll();
    }

    public List<Author> getAllSummary () {
        return authorRepository.findAllSummary();
    }

    public Author getByIdDetail(Long authorId) {
        return authorRepository.findById(authorId).orElseThrow(AuthorNotFoundException::new);
    }

    public Author getByIdSummary(Long authorId) {
        Author author = authorRepository.findByIdSummary(authorId);
        if (author == null) {
            throw new BookNotFoundException();
        }
        return author;
    }

    public Author save(Author author) {
        return  authorRepository.save(author);
    }

    public void deleteById(Long authorId) {
        if(authorRepository.findById(authorId).isEmpty()) {
            throw new AuthorNotFoundException();
        }
        authorRepository.deleteById(authorId);
    }

    public Author update(Author author) {
        if(authorRepository.findById(author.getId()).isEmpty()) {
            throw new AuthorNotFoundException();
        }
        Author author1 = new Author();
        author1.setId(author.getId());
        author1.setFirstName(author.getFirstName());
        author1.setLastName(author.getLastName());
        return authorRepository.save(author1);
    }
}
