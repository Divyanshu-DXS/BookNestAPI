package com.api.book.bootrestbook.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.bootrestbook.Entities.Book;
import com.api.book.bootrestbook.services.BookService;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books/{bookID}")
    public Book getBook(@PathVariable("bookID") int bookId) {
        // Book book = new Book();
        // book.setBookID(1001);
        // book.setTitle("The world to building REST APIs");
        // book.setAuthor("Dev-DXS");

        return this.bookService.getBookByID(bookId);
    }

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return this.bookService.getAllBooks();
    }

    @PostMapping("/books")
    public Book addBook(@RequestBody Book book) {

        Book b = this.bookService.addBook(book);
        return b;
    }

    @DeleteMapping("/books/{bookID}")
    public List<Book> deleteBookByID(@PathVariable int bookID) {

        return this.bookService.deleteBookByID(bookID);
    }

    @PutMapping("/books/{bookID}")
    public void updateBookById(@RequestBody Book book, @PathVariable("bookID") int bookID) {
        this.bookService.updateBookById(book, bookID);
    }

}
