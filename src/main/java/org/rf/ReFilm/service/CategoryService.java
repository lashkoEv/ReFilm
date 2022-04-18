package org.rf.ReFilm.service;

import org.rf.ReFilm.model.Category;
import org.rf.ReFilm.SQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    @SQLQuery(value = "select * from categories where id = ?1",
            objectType = "Category")
    Category findById(Long id);

    @SQLQuery(value = "select * from categories",
            objectType = "List<Category>")
    public List<Category> findAll();

    @SQLQuery(value = "INSERT INTO categories (category) values ('?1.category')",
            objectType = "Category")
    Category save(Category category);

    @SQLQuery(value = "delete from categories where id = ?1",
            objectType = "None")
    void delete(Long id);

    @SQLQuery(value = "select * from categories limit ?1.top, ?1.count",
            objectType = "Page<Category>")
    Page<Category> findAll(Pageable pageable);

    @SQLQuery(value = "select * from categories c where c.category like %?1% limit ?1.top, ?1.count",
            objectType = "Page<Category>")
    Page<Category> findSearchedCategories(String search, Pageable pageable);
}
