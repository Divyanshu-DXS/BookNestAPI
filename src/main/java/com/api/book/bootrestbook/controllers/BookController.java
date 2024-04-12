package com.api.book.bootrestbook.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.NotFound;

import com.api.book.bootrestbook.Entities.Book;
import com.api.book.bootrestbook.services.BookService;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books/{bookID}")
    public ResponseEntity<Book> getBook(@PathVariable("bookID") int bookId) {

        Book book = bookService.getBookByID(bookId);

        if (book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        } else
            return ResponseEntity.of(Optional.of(book));
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {

        List<Book> list = bookService.getAllBooks();

        if (list.size() <= 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else
            return ResponseEntity.of(Optional.of(list));

    }

    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book b = null;

        try {
            b = this.bookService.addBook(book);

            return ResponseEntity.of(Optional.of(b));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @DeleteMapping("/books/{bookID}")
    public ResponseEntity<String> deleteBookByID(@PathVariable int bookID) {
        try {
            return ResponseEntity.of(Optional.of("This Book with ID:" + bookID + " has now been Deleted !"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            // TODO: handle exception
        }

    }

    @PutMapping("/books/{bookID}")
    public ResponseEntity<?> updateBookById(@RequestBody Book book, @PathVariable("bookID") int bookID) {

        try {
            this.bookService.updateBookById(book, bookID);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
