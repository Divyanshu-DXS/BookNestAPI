package com.api.book.bootrestbook.services;

import java.util.List;
import java.util.stream.Collectors;

<<<<<<< HEAD
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
>>>>>>> 8bffaf3862a7590d97394edb7980c82c51e1f009
import org.springframework.stereotype.Component;

import com.api.book.bootrestbook.Entities.Book;

@Component
public class BookService {

    private static List<Book> list = new ArrayList<>();

    static {
        list.add(new Book(1001, "Create your API", "Dev-DXS"));
        list.add(new Book(1002, "Configuring APIs", "Dev-DXS"));
        list.add(new Book(1003, "Depth Understaing Of Application Architecture",
                "Dev-DXS"));
        list.add(new Book(1004, "World Of Devlopment", "DXS_Edu"));
    }

    // method to return list of all the books
    public List<Book> getAllBooks() {
        return list;
    }

    // method to return a particualr book when searched by ID
    public Book getBookByID(int bookID) {

        // Using stream
        Book b2 = null;
        try {
            b2 = list.stream().filter(e -> e.getBookID() == bookID).findFirst().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b2;

        // forEach

        /*
         * for (Book book : list) {
         * if(book.getBookID() == bookID){
         * return book;
         * }
         * } return null;
         */
    }

    // Adding a book
    public Book addBook(Book b) {

        list.add(b);
        return b;
    }

    // Deleting a book
    // public List<Book> deleteBookByID(int bookID) {

<<<<<<< HEAD
        Book b1 = getBookByID(bookID);
        list.remove(b1);
        // list = list.stream().filter(e -> e.getBookID() !=
        // bookID).collect(Collectors.toList());
        return list;
=======
    // this.bookRepo.deleteById(bookID);
    // List<Book> list = getAllBooks();
    // return list;
    // }
    // Deleting a book by ID
    public void deleteBookByID(int bookID) {
        try {
            bookRepo.deleteById(bookID);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("Book not found"); // Book with given ID not found
        }
>>>>>>> 8bffaf3862a7590d97394edb7980c82c51e1f009
    }

    // Updating a book
    public void updateBookById(Book book, int bookID) {

        Book b = getBookByID(bookID);
        b.setTitle(book.getTitle());
        b.setAuthor(book.getAuthor());
        // list = list.stream().map(e -> {
        // if (e.getBookID() == bookID) {
        // e.setTitle(book.getTitle());
        // e.setAuthor(book.getAuthor());
        // }
        // return e;
        // }).collect(Collectors.toList());
    }
}
