package org.rf.ReFilm.service;

import org.rf.ReFilm.model.Country;
import org.rf.ReFilm.model.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GenreService {
    Genre findById(Long id);

    public List<Genre> findAll();

    //Page<Film> findAll(Pageable pageable);

    Genre findByGenre(String search);

    Page<Genre> findSearchedGenres(String search, Pageable pageable);
}
