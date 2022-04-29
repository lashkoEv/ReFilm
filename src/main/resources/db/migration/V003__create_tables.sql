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
