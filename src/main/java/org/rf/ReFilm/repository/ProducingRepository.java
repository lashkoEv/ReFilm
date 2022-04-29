package org.rf.ReFilm.repository;

import org.rf.ReFilm.model.Producing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProducingRepository extends JpaRepository<Producing, Long> {
    List<Producing> findAllByCountryId(Long id);

    List<Producing> findAllByFilmId(Long id);
}
