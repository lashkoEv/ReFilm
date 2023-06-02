package org.rf.ReFilm.repository;

import org.rf.ReFilm.model.Categorization;
import org.rf.ReFilm.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategorizationRepository extends JpaRepository<Categorization, Long> {
    List<Categorization> findAllByGenreId(Long id);

    List<Categorization> findAllByFilmId(Long id);

    @Query("select g from Genre g, Categorization c where g.id=c.genreId and c.filmId=:id")
    List<Genre> findAllByFilmIdNames(Long id);

    void deleteAllByFilmId(Long id);

    void deleteAllByFilmIdAndGenreId(Long filmId, Long genreId);
}
