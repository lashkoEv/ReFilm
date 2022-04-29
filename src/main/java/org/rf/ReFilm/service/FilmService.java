package org.rf.ReFilm.service;

import org.rf.ReFilm.model.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FilmService {
    Film findById(Long id);

    public List<Film> findAll();

    Film save(Film film);

    void delete(Long id);

    Page<Film> findAll(Pageable pageable);

    Page<Film> findSearchedFilms(String search, Pageable pageable);

    List<Film> findByGenreIds(String[] ids);

    List<Film> findByCountryIds(String[] ids);
}
