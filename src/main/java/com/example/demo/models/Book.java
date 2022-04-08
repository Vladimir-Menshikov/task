package com.example.demo.models;

import com.example.demo.validators.ValidDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Book{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ValidDate
    private LocalDate releaseDate;
    private BigDecimal price;

    @Size(min = 13, max = 13)

    @Column(unique=true)
    private String isbn;

    @ManyToOne
    @JoinColumn(name="author_id")
    @JsonIgnoreProperties("books")
    private Author author;

    public Book() {
    }

    public Book(String name, String isbn, LocalDate releaseDate, BigDecimal price) {
        this.name = name;
        this.isbn = isbn;
        this.releaseDate = releaseDate;
        this.price = price;
    }

    public Book(Long id, String name, String isbn, LocalDate releaseDate, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.isbn = isbn;
        this.releaseDate = releaseDate;
        this.price = price;
    }
}