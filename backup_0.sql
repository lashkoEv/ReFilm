-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Версия сервера: 10.4.14-MariaDB
-- Версия PHP: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `refilm`
--

-- --------------------------------------------------------

--
-- Структура таблицы `authorities`
--

CREATE TABLE `authorities` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `authority` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `authorities`
--

INSERT INTO `authorities` (`id`, `user_id`, `authority`) VALUES
(1, 1, 'ROLE_ADMIN'),
(100, 100, 'ROLE_EXPERT'),
(101, 101, 'ROLE_EXPERT'),
(106, 102, 'ROLE_EXPERT'),
(107, 104, 'ROLE_EXPERT');

-- --------------------------------------------------------

--
-- Структура таблицы `categorization`
--

CREATE TABLE `categorization` (
  `id` bigint(20) NOT NULL,
  `genre_id` bigint(20) NOT NULL,
  `film_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `categorization`
--

INSERT INTO `categorization` (`id`, `genre_id`, `film_id`) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 1, 2),
(4, 2, 1),
(5, 3, 3),
(6, 4, 4),
(29, 13, 12),
(30, 14, 12),
(31, 17, 12),
(38, 8, 14),
(39, 9, 14),
(40, 18, 14),
(41, 28, 14);

-- --------------------------------------------------------

--
-- Структура таблицы `countries`
--

CREATE TABLE `countries` (
  `id` bigint(20) NOT NULL,
  `country` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `countries`
--

INSERT INTO `countries` (`id`, `country`) VALUES
(1, 'Австралія'),
(2, 'Австрія'),
(3, 'Азербайджан'),
(4, 'Албанія'),
(5, 'Андорра'),
(6, 'Аргентина'),
(7, 'Багами'),
(8, 'Барбадос'),
(9, 'Бахрейн'),
(10, 'Беліз'),
(11, 'Бельгія'),
(12, 'Білорусь'),
(13, 'Болгарія'),
(14, 'Болівія'),
(15, 'Боснія і Герцеговина'),
(16, 'Ботсвана'),
(17, 'Бразилія'),
(18, 'Бруней'),
(19, 'Бурунді'),
(20, 'Вануату'),
(21, 'Венесуела'),
(22, 'Великобританія'),
(23, 'Вірменія'),
(24, 'Гайана'),
(25, 'Гватемала'),
(26, 'Гондурас'),
(27, 'Гренада'),
(28, 'Греція'),
(29, 'Грузія'),
(30, 'Данія'),
(31, 'Домініканська Республіка'),
(32, 'Еквадор'),
(33, 'Естонія'),
(34, 'Ізраїль'),
(35, 'Індія'),
(36, 'Ірландія'),
(37, 'Іспанія'),
(38, 'Італія'),
(39, 'Казахстан'),
(40, 'Киргизстан'),
(41, 'Китай'),
(42, 'Кіпр'),
(43, 'Колумбія'),
(44, 'Нідерланди'),
(45, 'Косово'),
(46, 'Коста-Рика'),
(47, 'Латвія'),
(48, 'Литва'),
(49, 'Ліхтенштейн'),
(50, 'Люксембург'),
(51, 'Маврикій'),
(52, 'Мальта'),
(53, 'Марокко'),
(54, 'Мексика'),
(55, 'Молдова'),
(56, 'Монако'),
(57, 'Монголія'),
(58, 'Намібія'),
(59, 'Нікарагуа'),
(60, 'Німеччина'),
(61, 'Нова Зеландія'),
(62, 'Норвегія'),
(63, 'Оман'),
(64, 'Панама'),
(65, 'Парагвай'),
(66, 'Перу'),
(67, 'ПАР'),
(68, 'Польща'),
(69, 'Португалія'),
(70, 'Республіка Корея'),
(71, 'Росія'),
(72, 'Румунія'),
(73, 'Сан-Марино'),
(74, 'Сербія'),
(75, 'Сінгапур'),
(76, 'Словаччина'),
(77, 'Словенія'),
(78, 'США'),
(79, 'Таджикистан'),
(80, 'Туніс'),
(81, 'Туреччина'),
(82, 'Угорщина'),
(83, 'Узбекистан'),
(84, 'Україна'),
(85, 'Філіппіни'),
(86, 'Фінляндія'),
(87, 'Франція'),
(88, 'Хорватія'),
(89, 'Чехія'),
(90, 'Чорногорія'),
(91, 'Швейцарія'),
(92, 'Швеція'),
(93, 'Японія');

-- --------------------------------------------------------

--
-- Структура таблицы `films`
--

CREATE TABLE `films` (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `premiere` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `directors` varchar(255) NOT NULL,
  `screenwriters` varchar(255) NOT NULL,
  `actors` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `films`
--

INSERT INTO `films` (`id`, `name`, `premiere`, `directors`, `screenwriters`, `actors`) VALUES
(1, '1 film', '2021-01-01 13:16:00', '1 director', '1 screenwriter', '1 actor'),
(2, '2 film', '2021-01-01 13:16:00', '2 director', '2 screenwriter', '2 actor'),
(3, '3 film', '2021-01-01 13:16:00', '3 director', '3 screenwriter', '3 actor'),
(4, '4 film', '2021-01-01 13:16:00', '4 director', '4 screenwriter', '4 actor'),
(12, 'Film 6', '2022-04-28 21:00:00', 'Film 6', 'Film 6', 'Film 6'),
(14, 'rt bhtyjh t ', '2022-04-08 21:00:00', 'rt hrt hgrt', 'r tr ty ry', 'rt hrthrt hrht r hrthrth rh rth ');

-- --------------------------------------------------------

--
-- Структура таблицы `flyway_schema_history`
--

CREATE TABLE `flyway_schema_history` (
  `installed_rank` int(11) NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int(11) DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `execution_time` int(11) NOT NULL,
  `success` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `flyway_schema_history`
--

INSERT INTO `flyway_schema_history` (`installed_rank`, `version`, `description`, `type`, `script`, `checksum`, `installed_by`, `installed_on`, `execution_time`, `success`) VALUES
(1, '001', 'create tables', 'SQL', 'V001__create_tables.sql', 884532782, 'root', '2022-04-29 02:58:16', 11, 1),
(2, '002', 'create tables', 'SQL', 'V002__create_tables.sql', -20466403, 'root', '2022-04-29 02:58:16', 13, 1),
(3, '003', 'create tables', 'SQL', 'V003__create_tables.sql', -2081394974, 'root', '2022-04-29 02:58:16', 36, 1),
(4, '004', 'insert posts', 'SQL', 'V004__insert_posts.sql', 1806293298, 'root', '2022-04-29 02:58:17', 33, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `genres`
--

CREATE TABLE `genres` (
  `id` bigint(20) NOT NULL,
  `genre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `genres`
--

INSERT INTO `genres` (`id`, `genre`) VALUES
(1, 'аніме'),
(2, 'біографічний'),
(3, 'бойовик'),
(4, 'вестерн'),
(5, 'військовий'),
(6, 'детектив'),
(7, 'дитячий'),
(8, 'документальний'),
(9, 'драма'),
(10, 'історичний'),
(11, 'кінокомікс'),
(12, 'комедія'),
(13, 'концерт'),
(14, 'короткометражний'),
(15, 'кримінал'),
(16, 'мелодрама'),
(17, 'містика'),
(18, 'музика'),
(19, 'мультфільм'),
(20, 'мюзикл'),
(21, 'науковий'),
(22, 'нуар'),
(23, 'пригоди'),
(24, 'реаліті шоу'),
(25, 'сімейний'),
(26, 'спорт'),
(27, 'ток шоу'),
(28, 'трилер'),
(29, 'жахи'),
(30, 'фантастика'),
(31, 'фентезі');

-- --------------------------------------------------------

--
-- Структура таблицы `posts`
--

CREATE TABLE `posts` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `title` varchar(100) NOT NULL,
  `body` text NOT NULL,
  `created_ts` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `film_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `posts`
--

INSERT INTO `posts` (`id`, `user_id`, `title`, `body`, `created_ts`, `film_id`) VALUES
(1, 100, 'It`s a title for your post!', 'It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! ', '2022-01-01 05:00:00', 1),
(2, 100, 'It`s a title for your post!', 'It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! ', '2022-01-01 06:00:00', 2),
(3, 100, 'It`s a title for your post!', 'It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! ', '2022-01-01 06:15:00', 3),
(4, 100, 'It`s a title for your post!', 'It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! ', '2022-01-01 07:00:00', 4);

-- --------------------------------------------------------

--
-- Структура таблицы `producing`
--

CREATE TABLE `producing` (
  `id` bigint(20) NOT NULL,
  `country_id` bigint(20) NOT NULL,
  `film_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `producing`
--

INSERT INTO `producing` (`id`, `country_id`, `film_id`) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 1, 2),
(4, 2, 1),
(5, 3, 3),
(6, 4, 4),
(32, 4, 12),
(33, 15, 12),
(34, 16, 12),
(35, 19, 12),
(42, 17, 14),
(43, 58, 14),
(44, 84, 14);

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(50) NOT NULL,
  `name` varchar(255) NOT NULL,
  `birth_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `enabled` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `email`, `name`, `birth_date`, `enabled`) VALUES
(1, 'adm', '{bcrypt}$2a$10$cyf5NfobcruKQ8XGjUJkEegr9ZWFqaea6vjpXWEaSqTa2xL9wjgQC', 'admin@gmail.com', 'Admin', '2000-07-09 21:00:00', 1),
(100, 'user0', '{bcrypt}$2a$10$cyf5NfobcruKQ8XGjUJkEegr9ZWFqaea6vjpXWEaSqTa2xL9wjgQC', 'juuko@gmail.com', 'Илля Терентьев', '1987-10-09 21:00:00', 1),
(101, 'user1', '{bcrypt}$2a$10$cyf5NfobcruKQ8XGjUJkEegr9ZWFqaea6vjpXWEaSqTa2xL9wjgQC', 'innokent@gmail.com', 'Петро Фролов', '1993-05-04 21:00:00', 1),
(102, 'user2', '{bcrypt}$2a$10$Vi49KzkEldxlFdn1zWO2hu6ljwgHRYjvNA4CpZs754Fw.nwS1WnjW', 'jnastiao@gmail.com', 'Анастасія Іванова111', '1983-05-03 21:00:00', 1),
(104, 'user0', '{bcrypt}$2a$10$1AkUoee1uNHRr.QF1xJ1KubjkT2E9hnRuXGtg7/9H2QP/p8LibU7i', 'smevlk2014@gmail.com', 'user0', '2013-06-28 21:00:00', 1);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `authorities`
--
ALTER TABLE `authorities`
  ADD PRIMARY KEY (`id`),
  ADD KEY `authorities_user_fk` (`user_id`);

--
-- Индексы таблицы `categorization`
--
ALTER TABLE `categorization`
  ADD PRIMARY KEY (`id`),
  ADD KEY `categorization_countries_fk` (`genre_id`),
  ADD KEY `categorization_films_fk` (`film_id`);

--
-- Индексы таблицы `countries`
--
ALTER TABLE `countries`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `films`
--
ALTER TABLE `films`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `flyway_schema_history`
--
ALTER TABLE `flyway_schema_history`
  ADD PRIMARY KEY (`installed_rank`),
  ADD KEY `flyway_schema_history_s_idx` (`success`);

--
-- Индексы таблицы `genres`
--
ALTER TABLE `genres`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `posts`
--
ALTER TABLE `posts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `posts_users_fk` (`user_id`),
  ADD KEY `posts_films_fk` (`film_id`);

--
-- Индексы таблицы `producing`
--
ALTER TABLE `producing`
  ADD PRIMARY KEY (`id`),
  ADD KEY `producing_users_fk` (`country_id`),
  ADD KEY `producing_films_fk` (`film_id`);

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `authorities`
--
ALTER TABLE `authorities`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=108;

--
-- AUTO_INCREMENT для таблицы `categorization`
--
ALTER TABLE `categorization`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT для таблицы `countries`
--
ALTER TABLE `countries`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=94;

--
-- AUTO_INCREMENT для таблицы `films`
--
ALTER TABLE `films`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT для таблицы `genres`
--
ALTER TABLE `genres`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT для таблицы `posts`
--
ALTER TABLE `posts`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT для таблицы `producing`
--
ALTER TABLE `producing`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT для таблицы `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=105;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `authorities`
--
ALTER TABLE `authorities`
  ADD CONSTRAINT `authorities_user_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;

--
-- Ограничения внешнего ключа таблицы `categorization`
--
ALTER TABLE `categorization`
  ADD CONSTRAINT `categorization_countries_fk` FOREIGN KEY (`genre_id`) REFERENCES `genres` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `categorization_films_fk` FOREIGN KEY (`film_id`) REFERENCES `films` (`id`) ON DELETE CASCADE;

--
-- Ограничения внешнего ключа таблицы `posts`
--
ALTER TABLE `posts`
  ADD CONSTRAINT `posts_films_fk` FOREIGN KEY (`film_id`) REFERENCES `films` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `posts_users_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;

--
-- Ограничения внешнего ключа таблицы `producing`
--
ALTER TABLE `producing`
  ADD CONSTRAINT `producing_films_fk` FOREIGN KEY (`film_id`) REFERENCES `films` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `producing_users_fk` FOREIGN KEY (`country_id`) REFERENCES `countries` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
