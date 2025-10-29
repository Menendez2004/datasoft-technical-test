package com.datasoft.service.implementation;

import com.datasoft.controller.response.GenreResponse;
import com.datasoft.repository.GenreRepository;
import com.datasoft.service.GenreService;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Implementation of GenreService interface.
 */
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<GenreResponse> getAllGenres() {
        return genreRepository.findAll()
                .stream()
                .map(this::toGenreResponse)
                .collect(Collectors.toList());
    }

    @Override
    public GenreResponse getGenreById(UUID id) {
        var genre = genreRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Genre not found with id: " + id));
        return toGenreResponse(genre);
    }

    private GenreResponse toGenreResponse(com.datasoft.repository.domain.Genre genre) {
        return new GenreResponse(genre.getId(), genre.getName());
    }
}

