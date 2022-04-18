package org.rf.ReFilm.repository;

import org.rf.ReFilm.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.username like %?1%")
    User findByUsername(String username);

    @Query(nativeQuery = true,
            countQuery = "SELECT count(*) from users, authorities where users.id = authorities.user_id and authority like('ROLE_EXPERT')",
            value = "select users.* from users, authorities where users.id = authorities.user_id and authority like('ROLE_EXPERT') order by user_id desc")
    Page<User> findUsersByAuthoritiesNative(Pageable pageable);

    @Query("select u from User u where u.name like %?1% or u.email like %?1% or u.username like %?1%")
    Page<User> findSearchedUsers(String search, Pageable pageable);
}