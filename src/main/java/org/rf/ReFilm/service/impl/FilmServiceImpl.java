package org.rf.ReFilm.service.impl;

import org.rf.ReFilm.model.Category;
import org.rf.ReFilm.model.Film;
import org.rf.ReFilm.repository.CategoryRepository;
import org.rf.ReFilm.repository.FilmReposiroty;
import org.rf.ReFilm.service.CategoryService;
import org.rf.ReFilm.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FilmServiceImpl implements FilmService {
    @Autowired
    FilmReposiroty filmReposiroty;

    @Override
    public Film findById(Long id) {
        return filmReposiroty.getOne(id);
    }

    @Override
    public List<Film> findAll() {
        return filmReposiroty.findAll();
    }

    @Override
    public Film save(Film film) {
        return filmReposiroty.save(film);
    }

    @Override
    public void delete(Long id) {
        filmReposiroty.deleteById(id);
    }

    @Override
    public Page<Film> findAll(Pageable pageable) {
        return filmReposiroty.findAll(pageable);
    }

    @Override
    public Page<Film> findSearchedFilms(String search, Pageable pageable) {
        return filmReposiroty.findSearchedFilms(search, pageable);
    }
}
