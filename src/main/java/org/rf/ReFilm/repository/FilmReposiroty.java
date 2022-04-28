package org.rf.ReFilm.repository;

import org.rf.ReFilm.model.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FilmReposiroty extends JpaRepository<Film, Long> {
    @Query("select f from Film f where f.name like %?1% or f.actors like %?1% or f.directors like %?1% or f.premiere = ?1 or f.screenwriters like %?1%")
    Page<Film> findSearchedFilms(String search, Pageable pageable);
}
