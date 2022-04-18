package org.rf.ReFilm.repository;

import org.rf.ReFilm.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByIdDesc();
    List<Post> findAllByUserId(Long id);

    @Query("select p from Post p, User u where p.user = u and " +
            "(p.body like %?1% or p.title like %?1%" +
            "or u.username like %?1% or u.email like %?1% or u.name like %?1%)")
    Page<Post> findSearchedPosts(String search, Pageable pageable);

   // List<Post> findAllByCategoryId(Long id);
}