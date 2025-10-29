package com.datasoft.controller;

import com.datasoft.configuration.web.ApiResponse;
import com.datasoft.controller.request.BookUpdateRequest;
import com.datasoft.controller.response.BookResponse;
import com.datasoft.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse<BookResponse>> getBookById(@PathVariable UUID id) {
        BookResponse book = bookService.getBookById(id);
        return ResponseEntity.ok(new ApiResponse<>(book));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<BookResponse>>> getAllBooks() {
        List<BookResponse> books = bookService.getAllBooks();
        return ResponseEntity.ok(new ApiResponse<>(books));
    }

    @GetMapping("/genre/{genreId}")
    public ResponseEntity<ApiResponse<List<BookResponse>>> getBooksByGenre(@PathVariable UUID genreId) {
        List<BookResponse> books = bookService.getBooksByGenre(genreId);
        return ResponseEntity.ok(new ApiResponse<>(books));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BookResponse>> updateBook(@PathVariable UUID id,
                                                                @Valid @RequestBody BookUpdateRequest updateRequest) {
        BookResponse updatedBook = bookService.updateBook(id, updateRequest);
        return ResponseEntity.ok(new ApiResponse<>(updatedBook));
    }
}
