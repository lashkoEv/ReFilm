package org.rf.ReFilm.service;

import org.rf.ReFilm.model.Categorization;
import org.rf.ReFilm.model.Category;
import org.rf.ReFilm.model.Film;
import org.rf.ReFilm.model.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategorizationService {

    Categorization findById(Long id);

    public List<Categorization> findAll();

    Categorization save(Categorization categorization);

    void delete(Long id);

  //  Page<Categorization> findAll(Pageable pageable);

    List<Categorization> findByGenre(Genre genre);

    List<Categorization> findByFilm(Film film);
}
