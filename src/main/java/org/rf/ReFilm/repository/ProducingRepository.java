package org.rf.ReFilm.repository;

import org.rf.ReFilm.model.Country;
import org.rf.ReFilm.model.Producing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProducingRepository extends JpaRepository<Producing, Long> {
    List<Producing> findAllByCountryId(Long id);

    List<Producing> findAllByFilmId(Long id);

    @Query("select c from Country c, Producing p where c.id=p.countryId and p.filmId=:id")
    List<Country> findAllByFilmIdNames(Long id);

    void deleteAllByFilmId(Long id);

    void deleteAllByFilmIdAndCountryId(Long idFilm, Long idCountry);
}
