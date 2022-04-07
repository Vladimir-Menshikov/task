package com.example.demo.repositories;

import com.example.demo.models.Author;
import com.example.demo.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("select new com.example.demo.models.Author(a.id, a.firstName, a.lastName) from Author a")
    List<Author> findAllSummary();

   @Query("select new com.example.demo.models.Author(a.firstName, a.lastName) from Author a where a.id = :id")
    Author findByIdSummary(@Param("id") Long id);
}
