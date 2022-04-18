drop table if EXISTS authorities;
drop table if EXISTS users;

CREATE TABLE users
(
    id         bigint(20)   NOT NULL UNIQUE AUTO_INCREMENT,
    username   varchar(50)  NOT NULL,
    password   varchar(255) NOT NULL,
    email      varchar(50)  NOT NULL,
    name       varchar(255) NOT NULL,
    birth_date timestamp    NOT NULL,
    enabled    tinyint      NULL DEFAULT 1,
    PRIMARY KEY (id)
);

CREATE TABLE authorities
(
    id        bigint(20)   NOT NULL AUTO_INCREMENT,
    user_id   bigint(20)   NULL,
    authority varchar(255) NULL,
    PRIMARY KEY (id),
    CONSTRAINT authorities_user_fk FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);



-- pass
INSERT INTO users (username, password, email, name, birth_date) VALUES ('adm', '{bcrypt}$2a$10$cyf5NfobcruKQ8XGjUJkEegr9ZWFqaea6vjpXWEaSqTa2xL9wjgQC', 'admin@gmail.com', 'Admin', '2000-07-10');
INSERT INTO authorities (user_id, authority) VALUES (1, 'ROLE_ADMIN');

ALTER TABLE users AUTO_INCREMENT = 100;
ALTER TABLE authorities AUTO_INCREMENT = 100;

INSERT INTO users (username, password, name, birth_date, email) values('user0', '{bcrypt}$2a$10$cyf5NfobcruKQ8XGjUJkEegr9ZWFqaea6vjpXWEaSqTa2xL9wjgQC', 'Илля Терентьев', '1987-10-10', 'juuko@gmail.com');
SELECT @userId := LAST_INSERT_ID();
INSERT INTO authorities (user_id, authority) VALUES (@userId, 'ROLE_EXPERT');

INSERT INTO users (username, password, name, birth_date, email) values('user1', '{bcrypt}$2a$10$cyf5NfobcruKQ8XGjUJkEegr9ZWFqaea6vjpXWEaSqTa2xL9wjgQC', 'Петро Фролов', '1993-05-5', 'innokent@gmail.com');
SELECT @userId := LAST_INSERT_ID();
INSERT INTO authorities (user_id, authority) VALUES (@userId, 'ROLE_EXPERT');

INSERT INTO users (username, password, name, birth_date, email) values('user2', '{bcrypt}$2a$10$cyf5NfobcruKQ8XGjUJkEegr9ZWFqaea6vjpXWEaSqTa2xL9wjgQC', 'Анастасія Іванова', '1983-05-4', 'jnastiao@gmail.com');
SELECT @userId := LAST_INSERT_ID();
INSERT INTO authorities (user_id, authority) VALUES (@userId, 'ROLE_EXPERT');



drop table if EXISTS posts;
drop table if EXISTS films;
drop table if EXISTS countries;
drop table if EXISTS genres;
drop table if EXISTS categorization;
drop table if EXISTS producing;

CREATE TABLE countries
(
    id      bigint(20)   NOT NULL AUTO_INCREMENT,
    country varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE genres
(
    id    bigint(20)   NOT NULL AUTO_INCREMENT,
    genre varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE films
(
    id            bigint(20)   NOT NULL AUTO_INCREMENT,
    name          varchar(100) NOT NULL,
    premiere      TIMESTAMP    NOT NULL,
    directors     varchar(255) NOT NULL,
    screenwriters varchar(255) NOT NULL,
    actors        varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE categorization
(
    id       bigint(20)   NOT NULL AUTO_INCREMENT,
    genre_id bigint(20) NOT NULL,
    film_id  bigint(20)   NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT categorization_countries_fk FOREIGN KEY (genre_id) REFERENCES genres (id) ON DELETE CASCADE,
    CONSTRAINT categorization_films_fk FOREIGN KEY (film_id) REFERENCES films (id) ON DELETE CASCADE
);

CREATE TABLE producing
(
    id         bigint(20)   NOT NULL AUTO_INCREMENT,
    country_id bigint(20) NOT NULL,
    film_id    bigint(20)   NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT producing_users_fk FOREIGN KEY (country_id) REFERENCES countries (id) ON DELETE CASCADE,
    CONSTRAINT producing_films_fk FOREIGN KEY (film_id) REFERENCES films (id) ON DELETE CASCADE
);

CREATE TABLE posts
(
    id         bigint(20)   NOT NULL AUTO_INCREMENT,
    user_id    bigint(20)   NOT NULL,
    title      varchar(100) NOT NULL,
    body       text         NOT NULL,
    created_ts TIMESTAMP    NOT NULL,
    film_id    bigint(20)   NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT posts_users_fk FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT posts_films_fk FOREIGN KEY (film_id) REFERENCES films (id) ON DELETE CASCADE
);

INSERT INTO countries (country) values ('Украина');
INSERT INTO countries (country) values ('США');
INSERT INTO countries (country) values ('Южная Корея');
INSERT INTO countries (country) values ('Великобритания');

INSERT INTO genres (genre) values ('Триллер');
INSERT INTO genres (genre) values ('Фантастика');
INSERT INTO genres (genre) values ('Фентези');
INSERT INTO genres (genre) values ('Мелодрамма');

INSERT INTO films (name, premiere, directors, screenwriters, actors) values('1 film', '2021-01-01 15:16', '1 director', '1 screenwriter', '1 actor');
INSERT INTO films (name, premiere, directors, screenwriters, actors) values('2 film', '2021-01-01 15:16', '2 director', '2 screenwriter', '2 actor');
INSERT INTO films (name, premiere, directors, screenwriters, actors) values('3 film', '2021-01-01 15:16', '3 director', '3 screenwriter', '3 actor');
INSERT INTO films (name, premiere, directors, screenwriters, actors) values('4 film', '2021-01-01 15:16', '4 director', '4 screenwriter', '4 actor');

INSERT INTO posts (user_id, title, body, created_ts, film_id) values(100, 'It`s a title for your post!', 'It`s a body for your post!', '2022-01-01 07:00', 1);
INSERT INTO posts (user_id, title, body, created_ts, film_id) values(100, 'It`s a title for your post!', 'It`s a body for your post!', '2022-01-01 08:00', 2);
INSERT INTO posts (user_id, title, body, created_ts, film_id) values(100, 'It`s a title for your post!', 'It`s a body for your post!', '2022-01-01 08:15', 3);
INSERT INTO posts (user_id, title, body, created_ts, film_id) values(100, 'It`s a title for your post!', 'It`s a body for your post!', '2022-01-01 09:00', 4);

INSERT INTO posts (user_id, title, body, created_ts, film_id) values(101, 'It`s a title for your post!', 'It`s a body for your post!', '2022-01-01 10:00', 1);
INSERT INTO posts (user_id, title, body, created_ts, film_id) values(101, 'It`s a title for your post!', 'It`s a body for your post!', '2022-01-01 11:00', 2);
INSERT INTO posts (user_id, title, body, created_ts, film_id) values(101, 'It`s a title for your post!', 'It`s a body for your post!', '2022-01-01 12:00', 3);
INSERT INTO posts (user_id, title, body, created_ts, film_id) values(101, 'It`s a title for your post!', 'It`s a body for your post!', '2022-01-01 13:00', 4);

INSERT INTO posts (user_id, title, body, created_ts, film_id) values(102, 'It`s a title for your post!', 'It`s a body for your post!', '2022-01-01 13:23', 1);
INSERT INTO posts (user_id, title, body, created_ts, film_id) values(102, 'It`s a title for your post!', 'It`s a body for your post!', '2022-01-01 14:01', 2);
INSERT INTO posts (user_id, title, body, created_ts, film_id) values(102, 'It`s a title for your post!', 'It`s a body for your post!', '2022-01-01 14:03', 3);
INSERT INTO posts (user_id, title, body, created_ts, film_id) values(102, 'It`s a title for your post!', 'It`s a body for your post!', '2022-01-01 15:16', 4);


INSERT INTO categorization (genre_id, film_id) values (1,1);
INSERT INTO categorization (genre_id, film_id) values (2,2);
INSERT INTO categorization (genre_id, film_id) values (3,3);
INSERT INTO categorization (genre_id, film_id) values (4,4);

INSERT INTO producing (country_id, film_id) values (1,1);
INSERT INTO producing (country_id, film_id) values (2,2);
INSERT INTO producing (country_id, film_id) values (3,3);
INSERT INTO producing (country_id, film_id) values (4,4);


