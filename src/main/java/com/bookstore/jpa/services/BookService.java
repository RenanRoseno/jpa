package com.bookstore.jpa.services;

import com.bookstore.jpa.dtos.BookRecordDTO;
import com.bookstore.jpa.models.Book;
import com.bookstore.jpa.models.Review;
import com.bookstore.jpa.repositories.AuthorRepository;
import com.bookstore.jpa.repositories.BookRepository;
import com.bookstore.jpa.repositories.PublisherRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    @Transactional
    public Book saveBook(BookRecordDTO bookRecordDTO) {
        Book book = new Book();
        book.setTitle(bookRecordDTO.title());
        book.setPublisher(publisherRepository.findById(bookRecordDTO.publisherId()).get());
        book.setAuthors(authorRepository.findAllById(bookRecordDTO.authorIds()).stream().collect(Collectors.toSet()));

        Review reviewModel = new Review();
        reviewModel.setComment(bookRecordDTO.reviewComment());
        reviewModel.setBook(book);
        book.setReview(reviewModel);

        return bookRepository.save(book);
    }

}
