package org.rf.ReFilm.service;

import org.rf.ReFilm.SQLQuery;
import org.rf.ReFilm.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {

    Post findById(Long id);

    List<Post> findAll();

    Page<Post> findAll(Pageable pageable);

    Page<Post> findSearchedPosts(String search, Pageable pageable);

    Post save(Post post);

    void delete(Long id);

    List<Post> findAllByOrderByIdDesc();

    List<Post> findAllByUserId(Long id);

//    List<Post> findAllByCategoryId(Long id);
}
