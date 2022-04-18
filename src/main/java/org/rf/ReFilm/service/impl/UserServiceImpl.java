package org.rf.ReFilm.service.impl;

import org.rf.ReFilm.model.User;
import org.rf.ReFilm.repository.UserRepository;
import org.rf.ReFilm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User post) {
        return userRepository.save(post);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> findUsersByAuthoritiesNative(Pageable pageable) {
        return userRepository.findUsersByAuthoritiesNative(pageable);
    }

    @Override
    public Page<User> findSearchedUsers(String search, Pageable pageable) {
        return userRepository.findSearchedUsers(search, pageable);
    }
}
