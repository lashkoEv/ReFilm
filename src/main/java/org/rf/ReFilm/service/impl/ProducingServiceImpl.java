package org.rf.ReFilm.service.impl;

import org.rf.ReFilm.model.Country;
import org.rf.ReFilm.model.Film;
import org.rf.ReFilm.model.Producing;
import org.rf.ReFilm.repository.ProducingRepository;
import org.rf.ReFilm.service.ProducingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProducingServiceImpl implements ProducingService {
    @Autowired
    ProducingRepository producingRepository;

    @Override
    public Producing findById(Long id) {
        return producingRepository.getOne(id);
    }

    @Override
    public List<Producing> findAll() {
        return producingRepository.findAll();
    }

    @Override
    public Producing save(Producing producing) {
        return producingRepository.save(producing);
    }

    @Override
    public void delete(Long id) {
        producingRepository.deleteById(id);
    }

    @Override
    public List<Producing> findByCountry(Country country) {
        return producingRepository.findAllByCountryId(country.getId());
    }

    @Override
    public List<Producing> findByFilm(Film film) {
        return producingRepository.findAllByFilmId(film.getId());
    }
}
