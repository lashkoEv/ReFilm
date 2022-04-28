package org.rf.ReFilm.service.impl;

import org.rf.ReFilm.model.Post;
import org.rf.ReFilm.repository.PostRepository;
import org.rf.ReFilm.repository.UserRepository;
import org.rf.ReFilm.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    @Transactional(readOnly = true)
    public Post findById(Long id) {
        return postRepository.getOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public Page<Post> findSearchedPosts(String search, Pageable pageable) {
        return postRepository.findSearchedPosts(search, pageable);
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<Post> findAllByOrderByIdDesc() {
        return postRepository.findAllByOrderByIdDesc();
    }

    @Override
    public List<Post> findAllByUserId(Long id) {
        return postRepository.findAllByUserId(id);
    }

    @Override
    public List<Post> findAllByFilmId(Long id) {
        return postRepository.findAllByFilmId(id);
    }

//    @Override
//    public List<Post> findAllByCategoryId(Long id) {
//        return postRepository.findAllByCategoryId(id);
//    }
}
