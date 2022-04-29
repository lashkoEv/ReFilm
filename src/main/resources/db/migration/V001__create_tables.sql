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

