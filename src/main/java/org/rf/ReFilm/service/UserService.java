package org.rf.ReFilm.service;

import org.rf.ReFilm.SQLQuery;
import org.rf.ReFilm.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    @SQLQuery(value = "select users.* from users, authorities " +
            "where users.id = authorities.user_id and authority like('ROLE_EXPERT') " +
            "and users.id = ?1",
            objectType = "User")
    User findById(Long id);

    @SQLQuery(value = "select users.* from users, authorities " +
            "where users.id = authorities.user_id and authority like('ROLE_EXPERT') ",
            objectType = "List<User>")
    List<User> findAll();

    @SQLQuery(value = "update users set username='?1.username', password='?1.password', name='?1.name', birth_date='?1.birth_date', email='?1.email' where id=?1.id",
            objectType = "User")
//    @SQLQuery(value = "INSERT INTO users (username, password, name, birth_date, email) " +
//            "values('?1.username', '?1.password', '?1.name', '?1.birth_date', '?1.email');" +
//            "SELECT @userId := LAST_INSERT_ID();" +
//            "INSERT INTO authorities (user_id, authority) VALUES (@userId, 'ROLE_EXPERT');",
//            objectType = "User")
    User save(User user);

    @SQLQuery(value = "delete from users where id = ?1; " +
            "delete from authorities where user_id = ?1",
            objectType = "None")
    void delete(Long id);

    @SQLQuery(value = "select users.* from users, authorities " +
            "where users.id = authorities.user_id and authority like('ROLE_EXPERT') " +
            "and username like %?1%",
            objectType = "User")
    User findByUsername(String username);

    @SQLQuery(value ="select users.* from users, authorities " +
            "where users.id = authorities.user_id and authority like('ROLE_EXPERT') " +
            "limit ?1.top, ?1.count",
            objectType = "Page<User>")
    Page<User> findAll(Pageable pageable);

    @SQLQuery(value = "select users.* from users, authorities " +
            "where users.id = authorities.user_id and authority like('ROLE_EXPERT') " +
            "order by user_id desc limit ?1.top, ?1.count",
            objectType = "Page<User>")
    Page<User> findUsersByAuthoritiesNative(Pageable pageable);

    @SQLQuery(value = "select users.* from users, authorities " +
            "where users.id = authorities.user_id and authority like('ROLE_EXPERT') " +
            "and users.username like %?1% or users.name like %?1% or users.email like %?1% " +
            "order by user_id desc limit ?2.top, ?2.count",
            objectType = "Page<User>")
    Page<User> findSearchedUsers(String search, Pageable pageable);
}
