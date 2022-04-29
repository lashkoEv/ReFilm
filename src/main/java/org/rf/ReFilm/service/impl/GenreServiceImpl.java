package org.rf.ReFilm.service.impl;

import org.rf.ReFilm.model.Genre;
import org.rf.ReFilm.repository.CountryRepository;
import org.rf.ReFilm.repository.GenreRepository;
import org.rf.ReFilm.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GenreServiceImpl implements GenreService {
    @Autowired
    GenreRepository genreRepository;

    @Override
    public Genre findById(Long id) {
        return genreRepository.getOne(id);
    }

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Genre findByGenre(String search) {
        return genreRepository.findByGenre(search);
    }

    @Override
    public Page<Genre> findSearchedGenres(String search, Pageable pageable) {
        return genreRepository.findSearchedGenres(search, pageable);
    }
}
