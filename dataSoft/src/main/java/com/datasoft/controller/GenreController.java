package com.datasoft.controller;

import com.datasoft.configuration.web.ApiResponse;
import com.datasoft.controller.response.GenreResponse;
import com.datasoft.service.GenreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/genres")
@CrossOrigin(origins = "*")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<GenreResponse>>> getAllGenres() {
        List<GenreResponse> genres = genreService.getAllGenres();
        return ResponseEntity.ok(new ApiResponse<>(genres));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<GenreResponse>> getGenreById(@PathVariable UUID id) {
        GenreResponse genre = genreService.getGenreById(id);
        return ResponseEntity.ok(new ApiResponse<>(genre));
    }
}
