package com.datasoft.service;

import com.datasoft.controller.request.BookUpdateRequest;
import com.datasoft.controller.response.BookResponse;

import java.util.List;
import java.util.UUID;


public interface BookService {

    BookResponse getBookById(UUID id);

    List<BookResponse> getAllBooks();

    List<BookResponse> getBooksByGenre(UUID genreId);

    BookResponse updateBook(UUID id, BookUpdateRequest updateRequest);
}
