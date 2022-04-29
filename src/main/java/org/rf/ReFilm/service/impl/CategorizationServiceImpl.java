package org.rf.ReFilm.service.impl;

import org.rf.ReFilm.model.Categorization;
import org.rf.ReFilm.model.Category;
import org.rf.ReFilm.model.Film;
import org.rf.ReFilm.model.Genre;
import org.rf.ReFilm.repository.CategorizationRepository;
import org.rf.ReFilm.repository.CategoryRepository;
import org.rf.ReFilm.service.CategorizationService;
import org.rf.ReFilm.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategorizationServiceImpl implements CategorizationService {
    @Autowired
    CategorizationRepository categorizationRepository;

    @Override
    public Categorization findById(Long id) {
        return categorizationRepository.getOne(id);
    }

    @Override
    public List<Categorization> findAll() {
        return categorizationRepository.findAll();
    }

    @Override
    public Categorization save(Categorization categorization) {
        return categorizationRepository.save(categorization);
    }

    @Override
    public void delete(Long id) {
        categorizationRepository.deleteById(id);
    }

    @Override
    public List<Categorization> findByGenre(Genre genre) {
        return categorizationRepository.findAllByGenreId(genre.getId());
    }

    @Override
    public List<Categorization> findByFilm(Film film) {
        return categorizationRepository.findAllByFilmId(film.getId());
    }
}
