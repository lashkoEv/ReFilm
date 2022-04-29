package org.rf.ReFilm.repository;

import org.rf.ReFilm.model.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    @Query("select g from Genre g where g.genre like %?1%")
    Genre findByGenre(String search);

    @Query("select g from Genre g where g.genre like %?1%")
    Page<Genre> findSearchedGenres(String search, Pageable pageable);
}
