package com.datasoft.controller;
import com.datasoft.controller.request.BookUpdateRequest;
import com.datasoft.controller.response.BookResponse;
import com.datasoft.service.BookService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public BookResponse getBookById(@PathVariable UUID id) {
        return bookService.getBookById(id);
    }

    @GetMapping
    public List<BookResponse> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/genre/{genreId}")
    public List<BookResponse> getBooksByGenre(@PathVariable UUID genreId) {
        return bookService.getBooksByGenre(genreId);
    }

    @PutMapping("/{id}")
    public BookResponse updateBook(@PathVariable UUID id,
                                   @Valid @RequestBody BookUpdateRequest updateRequest) {
        return bookService.updateBook(id, updateRequest);
    }
}
