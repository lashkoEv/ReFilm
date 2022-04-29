package org.rf.ReFilm.service;

import org.rf.ReFilm.model.Country;
import org.rf.ReFilm.model.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CountryService {
    Country findById(Long id);

    public List<Country> findAll();

    //Page<Film> findAll(Pageable pageable);

    Country findByCountry(String search);

    Page<Country> findSearchedCountries(String search, Pageable pageable);
}
