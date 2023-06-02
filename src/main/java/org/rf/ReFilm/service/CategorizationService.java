package org.rf.ReFilm.service;

import org.rf.ReFilm.model.Categorization;
import org.rf.ReFilm.model.Film;
import org.rf.ReFilm.model.Genre;

import java.util.List;

public interface CategorizationService {

    Categorization findById(Long id);

    public List<Categorization> findAll();

    Categorization save(Categorization categorization);

    void delete(Long id);

  //  Page<Categorization> findAll(Pageable pageable);

    List<Categorization> findByGenre(Genre genre);

    List<Categorization> findByFilm(Film film);

    List<Genre> findAllByFilmIdNames(Long id);

    void deleteAllByFilmId(Long id);

    void deleteAllByFilmIdAndGenreId(Long filmId, Long genreId);
}
