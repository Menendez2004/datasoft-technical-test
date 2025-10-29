package com.datasoft.repository;

import com.datasoft.repository.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface BookRespository extends JpaRepository<com.datasoft.repository.domain.Book, UUID> {
    
    List<com.datasoft.repository.domain.Book> findByGenre(Genre genre);
    
    List<com.datasoft.repository.domain.Book> findByGenreId(UUID genreId);
    
    @Query("SELECT b FROM Book b WHERE b.genre.id = :genreId")
    List<com.datasoft.repository.domain.Book> findBooksByGenreId(@Param("genreId") UUID genreId);
    
    List<com.datasoft.repository.domain.Book> findByState(String state);
}
