package org.rf.ReFilm.service.impl;

import org.rf.ReFilm.model.Country;
import org.rf.ReFilm.repository.CountryRepository;
import org.rf.ReFilm.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CountryServiceImpl implements CountryService {
    @Autowired
    CountryRepository countryRepository;

    @Override
    public Country findById(Long id) {
        return countryRepository.getOne(id);
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country findByCountry(String search) {
        return countryRepository.findByCountry();
    }

    @Override
    public Page<Country> findSearchedCountries(String search, Pageable pageable) {
        return countryRepository.findSearchedCountries(search, pageable);
    }
}
