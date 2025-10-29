package com.datasoft.service.implementation;

import com.datasoft.controller.request.BookUpdateRequest;
import com.datasoft.controller.response.BookResponse;
import com.datasoft.controller.response.GenreResponse;
import com.datasoft.repository.BookRespository;
import com.datasoft.repository.GenreRepository;
import com.datasoft.repository.UserRepository;
import com.datasoft.service.BookService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Implementation of BookService interface.
 */
@Service
public class BookServiceImpl implements BookService {

    private final BookRespository bookRespository;
    private final GenreRepository genreRepository;
    private final UserRepository userRepository;

    public BookServiceImpl(BookRespository bookRespository,
                           GenreRepository genreRepository,
                           UserRepository userRepository) {
        this.bookRespository = bookRespository;
        this.genreRepository = genreRepository;
        this.userRepository = userRepository;
    }

    @Override
    public BookResponse getBookById(UUID id) {
        var book = bookRespository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));
        return toBookResponse(book);
    }

    @Override
    public List<BookResponse> getAllBooks() {
        var books = bookRespository.findAll();
        return books.stream()
                .map(this::toBookResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookResponse> getBooksByGenre(UUID genreId) {
        var books = bookRespository.findByGenreId(genreId);
        return books.stream()
                .map(this::toBookResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BookResponse updateBook(UUID id, BookUpdateRequest updateRequest) {
        var book = bookRespository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));

        if (updateRequest.getName() != null) book.setName(updateRequest.getName());
        if (updateRequest.getSummary() != null) book.setSummary(updateRequest.getSummary());
        if (updateRequest.getPrice() != null) book.setPrice(updateRequest.getPrice());
        if (updateRequest.getState() != null) book.setState(updateRequest.getState());
        if (updateRequest.getImage() != null) book.setImage(updateRequest.getImage());

        if (updateRequest.getGenreId() != null) {
            var genre = genreRepository.findById(updateRequest.getGenreId())
                    .orElseThrow(() -> new EntityNotFoundException("Genre not found with id: " + updateRequest.getGenreId()));
            book.setGenre(genre);
        }

        if (updateRequest.getUserId() != null) {
            var user = userRepository.findById(updateRequest.getUserId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + updateRequest.getUserId()));
            book.setUser(user);
        }

        var savedBook = bookRespository.save(book);
        return toBookResponse(savedBook);
    }

    private BookResponse toBookResponse(com.datasoft.repository.domain.Book book) {
        GenreResponse genreResponse = new GenreResponse(
                book.getGenre().getId(),
                book.getGenre().getName()
        );
        return new BookResponse(
                book.getId(),
                book.getName(),
                book.getSummary(),
                book.getPrice(),
                book.getState(),
                book.getImage(),
                genreResponse
        );
    }
}

