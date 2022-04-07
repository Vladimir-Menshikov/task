package com.example.demo.repositories;

import com.example.demo.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import com.example.demo.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByName(String name);
    List<Book> findByReleaseDate(LocalDate date);
    List<Book> findByNameAndReleaseDate(String name, LocalDate date);

    @Query("select b from Book b join fetch b.author")
    List<Book> findAllDetail();

    @Query("select b from Book b join fetch b.author where b.id = :id")
    Book findByIdDetail(@Param("id") Long id);

    @Query("select new com.example.demo.models.Book(b.id, b.name, b.releaseDate, b.price) from Book b")
    List<Book> findAllSummary();

    @Query("select new com.example.demo.models.Book(b.name, b.releaseDate, b.price) from Book b where b.id = :id")
    Book findByIdSummary(@Param("id") Long id);
}
