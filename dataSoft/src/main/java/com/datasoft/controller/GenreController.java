package com.datasoft.controller;
import com.datasoft.controller.response.GenreResponse;
import com.datasoft.service.GenreService;
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
    public List<GenreResponse> getAllGenres() {
        return genreService.getAllGenres();
    }

    @GetMapping("/{id}")
    public GenreResponse getGenreById(@PathVariable UUID id) {
        return genreService.getGenreById(id);
    }
}
