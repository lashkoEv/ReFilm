package org.rf.ReFilm.repository;

import org.rf.ReFilm.model.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CountryRepository extends JpaRepository<Country, Long> {
    @Query("select c from Country c where c.country like %?1%")
    Country findByCountry();

    @Query("select c from Country c where c.country like %?1%")
    Page<Country> findSearchedCountries(String search, Pageable pageable);
}
