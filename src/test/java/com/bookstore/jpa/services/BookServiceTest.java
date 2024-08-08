package com.bookstore.jpa.services;

import com.bookstore.jpa.dtos.BookDTOFixture;
import com.bookstore.jpa.dtos.BookRecordDTO;
import com.bookstore.jpa.models.Author;
import com.bookstore.jpa.models.Book;
import com.bookstore.jpa.models.Publisher;
import com.bookstore.jpa.models.Review;
import com.bookstore.jpa.repositories.AuthorRepository;
import com.bookstore.jpa.repositories.BookRepository;
import com.bookstore.jpa.repositories.PublisherRepository;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

//@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class BookServiceTest {

    @InjectMocks
    BookService bookService;

    @Mock
    BookRepository bookRepository;

    @Mock
    AuthorRepository authorRepository;

    @Mock
    PublisherRepository publisherRepository;

    Book book;

    Publisher publisher;

    List<Author> authors = new ArrayList<>();

    Review review;

    BookRecordDTO bookRecordDTO;

    HashSet<UUID> authorIds = new HashSet<>();

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        publisher = new Publisher();
//        publisher.setId(UUID.fromString("12107d90-325a-42d4-acf9-d064b36ae18c"));
        publisher.setName("Pearson");
        publisher = entityManager.persist(publisher);

        Author author = new Author();
       // author.setId(UUID.fromString("12107d90-325a-42d4-acf9-d064b36ae18c"));
        author.setName("Teste");
        author = entityManager.persist(author);
        authors.add(author);


        review = new Review();
        //review.setId(UUID.fromString("12107d90-325a-42d4-acf9-d064b36ae18c"));
        review.setComment("Test comment");
        review = entityManager.persist(review);

        book = new Book();
        book.setId(UUID.fromString("7299a01b-6b40-42c9-97f4-8646c94303e9"));
        book.setTitle("Domain Drive Design");
        book.setPublisher(publisher);
        book.setReview(review);

        HashSet<Author> authorsSet = (HashSet<Author>) authors.stream().collect(Collectors.toSet());
        book.setAuthors(authorsSet);
        authorIds.add(author.getId());

        bookRecordDTO = BookDTOFixture.build(book.getTitle(), publisher.getId(), authorIds, review.getComment());

    }

    @Test
    void mustSaveBookSuccess(){
        when(publisherRepository.findById(publisher.getId())).thenReturn(Optional.of(publisher));
        when(authorRepository.findAllById(authorIds)).thenReturn(authors);

        when(bookRepository.save(book)).thenReturn(book);

        Book bookSaved = bookService.saveBook(bookRecordDTO);
        assertEquals(book, bookSaved);
    }
    //private final static Long ID = 1L;


}
