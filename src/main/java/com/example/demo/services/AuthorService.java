package com.example.demo.services;

import com.example.demo.exceptions.AuthorNotFoundException;
import com.example.demo.models.Author;
import com.example.demo.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAll() {
        return authorRepository.findAll();
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

    public Author getById(Long authorId) {
        return authorRepository.findById(authorId).orElseThrow(AuthorNotFoundException::new);
    }

    public Author update(Author author) {
        if(authorRepository.findById(author.getId()).isEmpty()) {
            throw new AuthorNotFoundException();
        }
        return authorRepository.save(author);
    }
}
