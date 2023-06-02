package org.rf.ReFilm.service;

import org.rf.ReFilm.model.*;

import java.util.List;

public interface ProducingService {
    Producing findById(Long id);

    public List<Producing> findAll();

    Producing save(Producing producing);

    void delete(Long id);

    //  Page<Categorization> findAll(Pageable pageable);

    List<Producing> findByCountry(Country country);

    List<Producing> findByFilm(Film film);

    List<Country> findAllByFilmIdNames(Long id);

    void deleteAllByFilmId(Long id);

    void deleteAllByFilmIdAndGenreId(Long idFilm, long idCountry);
}
