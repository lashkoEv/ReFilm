package org.rf.ReFilm.repository;

import org.rf.ReFilm.model.Categorization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategorizationRepository extends JpaRepository<Categorization, Long> {
    List<Categorization> findAllByGenreId(Long id);

    List<Categorization> findAllByFilmId(Long id);
}
