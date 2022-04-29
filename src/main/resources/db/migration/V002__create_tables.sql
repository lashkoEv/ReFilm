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
