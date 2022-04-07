package com.example.demo;

import com.example.demo.models.Book;
import com.example.demo.services.BookService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceUnitTest {

    @Autowired
    private BookService bookService;

    @Test
    public void whenApplicationStarts_thenHibernateCreatesInitialRecords() {
        //List<Book> books = bookService.list();

        //Assert.assertEquals(3, books.size());
    }

    @Test
    public void saveTest() {
        //bookService.saveBook(new Book("someBookName3"));

       //List<Book> books = bookService.list();

       // Assert.assertEquals(4, books.size());
    }

    @Test
    public void deleteTest() {
        //bookService.deleteBook(4L);

        //List<Book> books = bookService.list();

        //Assert.assertEquals(3, books.size());
    }

    @Test
    public void findByIdTest() {
        //Book bookFromDB = bookService.getBookById(3L);
        //Assert.assertEquals("someBookName1",bookFromDB.getName());
    }

    @Test
    public void updateTest() {
        //bookService.updateBook(3L);
        //Assert.assertEquals("Default", bookService.getBookById(3L).getName());
    }
}
