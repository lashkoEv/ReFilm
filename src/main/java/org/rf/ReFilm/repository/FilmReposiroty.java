package org.rf.ReFilm.repository;

import org.rf.ReFilm.model.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface FilmReposiroty extends JpaRepository<Film, Long> {
    @Query("select f from Film f where f.name like %?1% or f.actors like %?1% or f.directors like %?1% or f.screenwriters like %?1%")
    Page<Film> findSearchedFilms(String search, Pageable pageable);

    @Query("select f from Film f, Categorization c where f.id=c.filmId and c.genreId=:id")
    List<Film> findAllByGenre(Long id);

    @Query("select f from Film f, Producing p where f.id=p.filmId and p.countryId=:id")
    List<Film> findAllByCountry(Long id);
}
