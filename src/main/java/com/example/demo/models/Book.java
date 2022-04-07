package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Book{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private LocalDate releaseDate;
    private BigDecimal price;

    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="author_id")
    @JsonIgnoreProperties("books")
    private Author author;

    public Book() {
    }

    public Book(String name, LocalDate releaseDate, BigDecimal price) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.price = price;
    }

    public Book(Long id, String name, LocalDate releaseDate, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.price = price;
    }
}