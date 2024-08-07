package com.bookstore.jpa.controllers;

import com.bookstore.jpa.dtos.BookRecordDTO;
import com.bookstore.jpa.models.Book;
import com.bookstore.jpa.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("books")
public class BookController {
    private final BookService bookService;


    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<Book> saveBook(@RequestBody BookRecordDTO bookRecordDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.saveBook(bookRecordDTO));
    }
}
