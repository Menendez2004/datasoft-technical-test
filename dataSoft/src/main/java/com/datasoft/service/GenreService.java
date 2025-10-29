package com.datasoft.service;

import com.datasoft.controller.response.GenreResponse;

import java.util.List;
import java.util.UUID;

public interface GenreService {

    List<GenreResponse> getAllGenres();

    GenreResponse getGenreById(UUID id);
}
