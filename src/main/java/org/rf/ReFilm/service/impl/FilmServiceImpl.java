package org.rf.ReFilm.service.impl;

import org.rf.ReFilm.model.Film;
import org.rf.ReFilm.repository.FilmReposiroty;
import org.rf.ReFilm.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Override
    public List<Film> findByGenreIds(String[] ids) {
        List<Film> films = new ArrayList<Film>();
        for (String id : ids) {
            for(Film f : filmReposiroty.findAllByGenre(Long.parseLong(id))) {
                if(!films.contains(f)){
                    films.add(f);
                }
            }
        }
        return films;
    }

    @Override
    public List<Film> findByCountryIds(String[] ids) {
        List<Film> films = new ArrayList<Film>();
        for (String id : ids) {
            for(Film f : filmReposiroty.findAllByCountry(Long.parseLong(id))) {
                if(!films.contains(f)){
                   films.add(f);
                }
            }
        }
        return films;
    }
}
