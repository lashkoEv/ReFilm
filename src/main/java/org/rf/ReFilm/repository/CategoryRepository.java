package org.rf.ReFilm.repository;

import org.rf.ReFilm.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select c from Category c where c.category like %?1%")
    Page<Category> findSearchedCategories(String search, Pageable pageable);
}
