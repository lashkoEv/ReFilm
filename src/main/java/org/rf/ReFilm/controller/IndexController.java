package org.rf.ReFilm.controller;

import lombok.extern.slf4j.Slf4j;
import org.rf.ReFilm.model.Post;
import org.rf.ReFilm.service.UserService;
import org.rf.ReFilm.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@Slf4j
public class IndexController {

    private final PostService postService;

    public IndexController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value = {"/", "/home", "/index"})
    public String home(Model model, Integer page, Integer size, @Param("search") String search) {
        log.info(" --- index");
        Pageable pageable = PageRequest.of(page == null ? 0 : page, size == null ? 3 : size, Sort.by("id").descending());
        Page<Post> postPage;
        if (search != null) {
            postPage = postService.findSearchedPosts(search, pageable);
        } else {
            postPage = postService.findAll(pageable);
        }
        model.addAttribute("posts", postPage.getContent());
        model.addAttribute("page", postPage);
        return "index";
    }

    @RequestMapping("/about")
    public String about(Map<String, Object> model) {
        log.info(" --- about");
        model.put("message", "You are in about page!!!");
        return "about";
    }

    @GetMapping("/login")
    public String login() {
        log.info(" --- login");
        return "login";
    }

    @GetMapping("/success")
    public String authorizationSuccess(RedirectAttributes redirectAttributes) {
        log.info(" --- index successfully authorized");
        redirectAttributes.addFlashAttribute("msg", "Вхід виконано успішно!");
        return "redirect:/";
    }

    @GetMapping("/login/error")
    public String authorizationError(RedirectAttributes redirectAttributes) {
        log.info(" --- index authorisation error");
        redirectAttributes.addFlashAttribute("msgErr", "Помилка авторизації!");
        return "redirect:/";
    }

    @GetMapping("/logout/success")
    public String logout(RedirectAttributes redirectAttributes) {
        log.info(" --- index logout");
        redirectAttributes.addFlashAttribute("msg", "Вихід виконано успішно!");
        return "redirect:/";
    }

    @GetMapping("/admin")
    public String logging(Model model) {
        log.info(" --- index logging");
        List<String> res = new ArrayList<>();
        try {
            File file = new File("D:/refilm_log_file.log");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                sb.append(" ");
                sb.append(line);
                line = reader.readLine();
            }
            Pattern pattern = Pattern.compile("(Logged user: )([A-Za-z0-9])*");
            Matcher matcher = pattern.matcher(sb.toString());
            while (matcher.find()) {
                String str = sb.substring(matcher.start(), matcher.end());
                str += " " + sb.substring(1, 12);
                res.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("strings", res);
        return "admin";
    }

    @GetMapping("/admin/backup")
    public String backup(Model model) throws IOException {
        log.info(" --- backup db");
        String mysqlcmd = "mysqldump -u root - --opt maritime > D:/Study/DNU/Мaщенко/Web-технологии/ReFilm/ReFilm/backup_0.sql";
        writeSql();
        try {
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("cmd /c" + mysqlcmd);
            System.out.println("....success....");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("....error..." + e.getMessage());

        }

        List<String> res = new ArrayList<>();
        res.add("Бекап створено успішно!");
        res.add("Path: D:/Study/DNU/Мaщенко/Web-технологии/ReFilm/ReFilm/backup_0.sql");
        model.addAttribute("strings", res);
        return "admin";
    }























    private void writeSql() throws IOException {
        File file = new File("backup_0.sql");

        // Создание файла
        file.createNewFile();

        // Создание объекта FileWriter
        FileWriter writer = new FileWriter(file);

        // Запись содержимого в файл
        writer.write("-- phpMyAdmin SQL Dump\n" +
                "-- version 5.0.3\n" +
                "-- https://www.phpmyadmin.net/\n" +
                "--\n" +
                "-- Хост: 127.0.0.1\n" +
                "-- Версия сервера: 10.4.14-MariaDB\n" +
                "-- Версия PHP: 7.4.11\n" +
                "\n" +
                "SET SQL_MODE = \"NO_AUTO_VALUE_ON_ZERO\";\n" +
                "START TRANSACTION;\n" +
                "SET time_zone = \"+00:00\";\n" +
                "\n" +
                "\n" +
                "/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;\n" +
                "/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;\n" +
                "/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;\n" +
                "/*!40101 SET NAMES utf8mb4 */;\n" +
                "\n" +
                "--\n" +
                "-- База данных: `refilm`\n" +
                "--\n" +
                "\n" +
                "-- --------------------------------------------------------\n" +
                "\n" +
                "--\n" +
                "-- Структура таблицы `authorities`\n" +
                "--\n" +
                "\n" +
                "CREATE TABLE `authorities` (\n" +
                "  `id` bigint(20) NOT NULL,\n" +
                "  `user_id` bigint(20) DEFAULT NULL,\n" +
                "  `authority` varchar(255) DEFAULT NULL\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n" +
                "\n" +
                "--\n" +
                "-- Дамп данных таблицы `authorities`\n" +
                "--\n" +
                "\n" +
                "INSERT INTO `authorities` (`id`, `user_id`, `authority`) VALUES\n" +
                "(1, 1, 'ROLE_ADMIN'),\n" +
                "(100, 100, 'ROLE_EXPERT'),\n" +
                "(101, 101, 'ROLE_EXPERT'),\n" +
                "(106, 102, 'ROLE_EXPERT'),\n" +
                "(107, 104, 'ROLE_EXPERT');\n" +
                "\n" +
                "-- --------------------------------------------------------\n" +
                "\n" +
                "--\n" +
                "-- Структура таблицы `categorization`\n" +
                "--\n" +
                "\n" +
                "CREATE TABLE `categorization` (\n" +
                "  `id` bigint(20) NOT NULL,\n" +
                "  `genre_id` bigint(20) NOT NULL,\n" +
                "  `film_id` bigint(20) NOT NULL\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n" +
                "\n" +
                "--\n" +
                "-- Дамп данных таблицы `categorization`\n" +
                "--\n" +
                "\n" +
                "INSERT INTO `categorization` (`id`, `genre_id`, `film_id`) VALUES\n" +
                "(1, 1, 1),\n" +
                "(2, 2, 2),\n" +
                "(3, 1, 2),\n" +
                "(4, 2, 1),\n" +
                "(5, 3, 3),\n" +
                "(6, 4, 4),\n" +
                "(29, 13, 12),\n" +
                "(30, 14, 12),\n" +
                "(31, 17, 12),\n" +
                "(38, 8, 14),\n" +
                "(39, 9, 14),\n" +
                "(40, 18, 14),\n" +
                "(41, 28, 14);\n" +
                "\n" +
                "-- --------------------------------------------------------\n" +
                "\n" +
                "--\n" +
                "-- Структура таблицы `countries`\n" +
                "--\n" +
                "\n" +
                "CREATE TABLE `countries` (\n" +
                "  `id` bigint(20) NOT NULL,\n" +
                "  `country` varchar(255) NOT NULL\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n" +
                "\n" +
                "--\n" +
                "-- Дамп данных таблицы `countries`\n" +
                "--\n" +
                "\n" +
                "INSERT INTO `countries` (`id`, `country`) VALUES\n" +
                "(1, 'Австралія'),\n" +
                "(2, 'Австрія'),\n" +
                "(3, 'Азербайджан'),\n" +
                "(4, 'Албанія'),\n" +
                "(5, 'Андорра'),\n" +
                "(6, 'Аргентина'),\n" +
                "(7, 'Багами'),\n" +
                "(8, 'Барбадос'),\n" +
                "(9, 'Бахрейн'),\n" +
                "(10, 'Беліз'),\n" +
                "(11, 'Бельгія'),\n" +
                "(12, 'Білорусь'),\n" +
                "(13, 'Болгарія'),\n" +
                "(14, 'Болівія'),\n" +
                "(15, 'Боснія і Герцеговина'),\n" +
                "(16, 'Ботсвана'),\n" +
                "(17, 'Бразилія'),\n" +
                "(18, 'Бруней'),\n" +
                "(19, 'Бурунді'),\n" +
                "(20, 'Вануату'),\n" +
                "(21, 'Венесуела'),\n" +
                "(22, 'Великобританія'),\n" +
                "(23, 'Вірменія'),\n" +
                "(24, 'Гайана'),\n" +
                "(25, 'Гватемала'),\n" +
                "(26, 'Гондурас'),\n" +
                "(27, 'Гренада'),\n" +
                "(28, 'Греція'),\n" +
                "(29, 'Грузія'),\n" +
                "(30, 'Данія'),\n" +
                "(31, 'Домініканська Республіка'),\n" +
                "(32, 'Еквадор'),\n" +
                "(33, 'Естонія'),\n" +
                "(34, 'Ізраїль'),\n" +
                "(35, 'Індія'),\n" +
                "(36, 'Ірландія'),\n" +
                "(37, 'Іспанія'),\n" +
                "(38, 'Італія'),\n" +
                "(39, 'Казахстан'),\n" +
                "(40, 'Киргизстан'),\n" +
                "(41, 'Китай'),\n" +
                "(42, 'Кіпр'),\n" +
                "(43, 'Колумбія'),\n" +
                "(44, 'Нідерланди'),\n" +
                "(45, 'Косово'),\n" +
                "(46, 'Коста-Рика'),\n" +
                "(47, 'Латвія'),\n" +
                "(48, 'Литва'),\n" +
                "(49, 'Ліхтенштейн'),\n" +
                "(50, 'Люксембург'),\n" +
                "(51, 'Маврикій'),\n" +
                "(52, 'Мальта'),\n" +
                "(53, 'Марокко'),\n" +
                "(54, 'Мексика'),\n" +
                "(55, 'Молдова'),\n" +
                "(56, 'Монако'),\n" +
                "(57, 'Монголія'),\n" +
                "(58, 'Намібія'),\n" +
                "(59, 'Нікарагуа'),\n" +
                "(60, 'Німеччина'),\n" +
                "(61, 'Нова Зеландія'),\n" +
                "(62, 'Норвегія'),\n" +
                "(63, 'Оман'),\n" +
                "(64, 'Панама'),\n" +
                "(65, 'Парагвай'),\n" +
                "(66, 'Перу'),\n" +
                "(67, 'ПАР'),\n" +
                "(68, 'Польща'),\n" +
                "(69, 'Португалія'),\n" +
                "(70, 'Республіка Корея'),\n" +
                "(71, 'Росія'),\n" +
                "(72, 'Румунія'),\n" +
                "(73, 'Сан-Марино'),\n" +
                "(74, 'Сербія'),\n" +
                "(75, 'Сінгапур'),\n" +
                "(76, 'Словаччина'),\n" +
                "(77, 'Словенія'),\n" +
                "(78, 'США'),\n" +
                "(79, 'Таджикистан'),\n" +
                "(80, 'Туніс'),\n" +
                "(81, 'Туреччина'),\n" +
                "(82, 'Угорщина'),\n" +
                "(83, 'Узбекистан'),\n" +
                "(84, 'Україна'),\n" +
                "(85, 'Філіппіни'),\n" +
                "(86, 'Фінляндія'),\n" +
                "(87, 'Франція'),\n" +
                "(88, 'Хорватія'),\n" +
                "(89, 'Чехія'),\n" +
                "(90, 'Чорногорія'),\n" +
                "(91, 'Швейцарія'),\n" +
                "(92, 'Швеція'),\n" +
                "(93, 'Японія');\n" +
                "\n" +
                "-- --------------------------------------------------------\n" +
                "\n" +
                "--\n" +
                "-- Структура таблицы `films`\n" +
                "--\n" +
                "\n" +
                "CREATE TABLE `films` (\n" +
                "  `id` bigint(20) NOT NULL,\n" +
                "  `name` varchar(100) NOT NULL,\n" +
                "  `premiere` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),\n" +
                "  `directors` varchar(255) NOT NULL,\n" +
                "  `screenwriters` varchar(255) NOT NULL,\n" +
                "  `actors` varchar(255) NOT NULL\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n" +
                "\n" +
                "--\n" +
                "-- Дамп данных таблицы `films`\n" +
                "--\n" +
                "\n" +
                "INSERT INTO `films` (`id`, `name`, `premiere`, `directors`, `screenwriters`, `actors`) VALUES\n" +
                "(1, '1 film', '2021-01-01 13:16:00', '1 director', '1 screenwriter', '1 actor'),\n" +
                "(2, '2 film', '2021-01-01 13:16:00', '2 director', '2 screenwriter', '2 actor'),\n" +
                "(3, '3 film', '2021-01-01 13:16:00', '3 director', '3 screenwriter', '3 actor'),\n" +
                "(4, '4 film', '2021-01-01 13:16:00', '4 director', '4 screenwriter', '4 actor'),\n" +
                "(12, 'Film 6', '2022-04-28 21:00:00', 'Film 6', 'Film 6', 'Film 6'),\n" +
                "(14, 'rt bhtyjh t ', '2022-04-08 21:00:00', 'rt hrt hgrt', 'r tr ty ry', 'rt hrthrt hrht r hrthrth rh rth ');\n" +
                "\n" +
                "-- --------------------------------------------------------\n" +
                "\n" +
                "--\n" +
                "-- Структура таблицы `flyway_schema_history`\n" +
                "--\n" +
                "\n" +
                "CREATE TABLE `flyway_schema_history` (\n" +
                "  `installed_rank` int(11) NOT NULL,\n" +
                "  `version` varchar(50) DEFAULT NULL,\n" +
                "  `description` varchar(200) NOT NULL,\n" +
                "  `type` varchar(20) NOT NULL,\n" +
                "  `script` varchar(1000) NOT NULL,\n" +
                "  `checksum` int(11) DEFAULT NULL,\n" +
                "  `installed_by` varchar(100) NOT NULL,\n" +
                "  `installed_on` timestamp NOT NULL DEFAULT current_timestamp(),\n" +
                "  `execution_time` int(11) NOT NULL,\n" +
                "  `success` tinyint(1) NOT NULL\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n" +
                "\n" +
                "--\n" +
                "-- Дамп данных таблицы `flyway_schema_history`\n" +
                "--\n" +
                "\n" +
                "INSERT INTO `flyway_schema_history` (`installed_rank`, `version`, `description`, `type`, `script`, `checksum`, `installed_by`, `installed_on`, `execution_time`, `success`) VALUES\n" +
                "(1, '001', 'create tables', 'SQL', 'V001__create_tables.sql', 884532782, 'root', '2022-04-29 02:58:16', 11, 1),\n" +
                "(2, '002', 'create tables', 'SQL', 'V002__create_tables.sql', -20466403, 'root', '2022-04-29 02:58:16', 13, 1),\n" +
                "(3, '003', 'create tables', 'SQL', 'V003__create_tables.sql', -2081394974, 'root', '2022-04-29 02:58:16', 36, 1),\n" +
                "(4, '004', 'insert posts', 'SQL', 'V004__insert_posts.sql', 1806293298, 'root', '2022-04-29 02:58:17', 33, 1);\n" +
                "\n" +
                "-- --------------------------------------------------------\n" +
                "\n" +
                "--\n" +
                "-- Структура таблицы `genres`\n" +
                "--\n" +
                "\n" +
                "CREATE TABLE `genres` (\n" +
                "  `id` bigint(20) NOT NULL,\n" +
                "  `genre` varchar(255) NOT NULL\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n" +
                "\n" +
                "--\n" +
                "-- Дамп данных таблицы `genres`\n" +
                "--\n" +
                "\n" +
                "INSERT INTO `genres` (`id`, `genre`) VALUES\n" +
                "(1, 'аніме'),\n" +
                "(2, 'біографічний'),\n" +
                "(3, 'бойовик'),\n" +
                "(4, 'вестерн'),\n" +
                "(5, 'військовий'),\n" +
                "(6, 'детектив'),\n" +
                "(7, 'дитячий'),\n" +
                "(8, 'документальний'),\n" +
                "(9, 'драма'),\n" +
                "(10, 'історичний'),\n" +
                "(11, 'кінокомікс'),\n" +
                "(12, 'комедія'),\n" +
                "(13, 'концерт'),\n" +
                "(14, 'короткометражний'),\n" +
                "(15, 'кримінал'),\n" +
                "(16, 'мелодрама'),\n" +
                "(17, 'містика'),\n" +
                "(18, 'музика'),\n" +
                "(19, 'мультфільм'),\n" +
                "(20, 'мюзикл'),\n" +
                "(21, 'науковий'),\n" +
                "(22, 'нуар'),\n" +
                "(23, 'пригоди'),\n" +
                "(24, 'реаліті шоу'),\n" +
                "(25, 'сімейний'),\n" +
                "(26, 'спорт'),\n" +
                "(27, 'ток шоу'),\n" +
                "(28, 'трилер'),\n" +
                "(29, 'жахи'),\n" +
                "(30, 'фантастика'),\n" +
                "(31, 'фентезі');\n" +
                "\n" +
                "-- --------------------------------------------------------\n" +
                "\n" +
                "--\n" +
                "-- Структура таблицы `posts`\n" +
                "--\n" +
                "\n" +
                "CREATE TABLE `posts` (\n" +
                "  `id` bigint(20) NOT NULL,\n" +
                "  `user_id` bigint(20) NOT NULL,\n" +
                "  `title` varchar(100) NOT NULL,\n" +
                "  `body` text NOT NULL,\n" +
                "  `created_ts` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),\n" +
                "  `film_id` bigint(20) NOT NULL\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n" +
                "\n" +
                "--\n" +
                "-- Дамп данных таблицы `posts`\n" +
                "--\n" +
                "\n" +
                "INSERT INTO `posts` (`id`, `user_id`, `title`, `body`, `created_ts`, `film_id`) VALUES\n" +
                "(1, 100, 'It`s a title for your post!', 'It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! ', '2022-01-01 05:00:00', 1),\n" +
                "(2, 100, 'It`s a title for your post!', 'It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! ', '2022-01-01 06:00:00', 2),\n" +
                "(3, 100, 'It`s a title for your post!', 'It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! ', '2022-01-01 06:15:00', 3),\n" +
                "(4, 100, 'It`s a title for your post!', 'It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! It`s a body for your post! ', '2022-01-01 07:00:00', 4);\n" +
                "\n" +
                "-- --------------------------------------------------------\n" +
                "\n" +
                "--\n" +
                "-- Структура таблицы `producing`\n" +
                "--\n" +
                "\n" +
                "CREATE TABLE `producing` (\n" +
                "  `id` bigint(20) NOT NULL,\n" +
                "  `country_id` bigint(20) NOT NULL,\n" +
                "  `film_id` bigint(20) NOT NULL\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n" +
                "\n" +
                "--\n" +
                "-- Дамп данных таблицы `producing`\n" +
                "--\n" +
                "\n" +
                "INSERT INTO `producing` (`id`, `country_id`, `film_id`) VALUES\n" +
                "(1, 1, 1),\n" +
                "(2, 2, 2),\n" +
                "(3, 1, 2),\n" +
                "(4, 2, 1),\n" +
                "(5, 3, 3),\n" +
                "(6, 4, 4),\n" +
                "(32, 4, 12),\n" +
                "(33, 15, 12),\n" +
                "(34, 16, 12),\n" +
                "(35, 19, 12),\n" +
                "(42, 17, 14),\n" +
                "(43, 58, 14),\n" +
                "(44, 84, 14);\n" +
                "\n" +
                "-- --------------------------------------------------------\n" +
                "\n" +
                "--\n" +
                "-- Структура таблицы `users`\n" +
                "--\n" +
                "\n" +
                "CREATE TABLE `users` (\n" +
                "  `id` bigint(20) NOT NULL,\n" +
                "  `username` varchar(50) NOT NULL,\n" +
                "  `password` varchar(255) NOT NULL,\n" +
                "  `email` varchar(50) NOT NULL,\n" +
                "  `name` varchar(255) NOT NULL,\n" +
                "  `birth_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),\n" +
                "  `enabled` tinyint(4) DEFAULT 1\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n" +
                "\n" +
                "--\n" +
                "-- Дамп данных таблицы `users`\n" +
                "--\n" +
                "\n" +
                "INSERT INTO `users` (`id`, `username`, `password`, `email`, `name`, `birth_date`, `enabled`) VALUES\n" +
                "(1, 'adm', '{bcrypt}$2a$10$cyf5NfobcruKQ8XGjUJkEegr9ZWFqaea6vjpXWEaSqTa2xL9wjgQC', 'admin@gmail.com', 'Admin', '2000-07-09 21:00:00', 1),\n" +
                "(100, 'user0', '{bcrypt}$2a$10$cyf5NfobcruKQ8XGjUJkEegr9ZWFqaea6vjpXWEaSqTa2xL9wjgQC', 'juuko@gmail.com', 'Илля Терентьев', '1987-10-09 21:00:00', 1),\n" +
                "(101, 'user1', '{bcrypt}$2a$10$cyf5NfobcruKQ8XGjUJkEegr9ZWFqaea6vjpXWEaSqTa2xL9wjgQC', 'innokent@gmail.com', 'Петро Фролов', '1993-05-04 21:00:00', 1),\n" +
                "(102, 'user2', '{bcrypt}$2a$10$Vi49KzkEldxlFdn1zWO2hu6ljwgHRYjvNA4CpZs754Fw.nwS1WnjW', 'jnastiao@gmail.com', 'Анастасія Іванова111', '1983-05-03 21:00:00', 1),\n" +
                "(104, 'user0', '{bcrypt}$2a$10$1AkUoee1uNHRr.QF1xJ1KubjkT2E9hnRuXGtg7/9H2QP/p8LibU7i', 'smevlk2014@gmail.com', 'user0', '2013-06-28 21:00:00', 1);\n" +
                "\n" +
                "--\n" +
                "-- Индексы сохранённых таблиц\n" +
                "--\n" +
                "\n" +
                "--\n" +
                "-- Индексы таблицы `authorities`\n" +
                "--\n" +
                "ALTER TABLE `authorities`\n" +
                "  ADD PRIMARY KEY (`id`),\n" +
                "  ADD KEY `authorities_user_fk` (`user_id`);\n" +
                "\n" +
                "--\n" +
                "-- Индексы таблицы `categorization`\n" +
                "--\n" +
                "ALTER TABLE `categorization`\n" +
                "  ADD PRIMARY KEY (`id`),\n" +
                "  ADD KEY `categorization_countries_fk` (`genre_id`),\n" +
                "  ADD KEY `categorization_films_fk` (`film_id`);\n" +
                "\n" +
                "--\n" +
                "-- Индексы таблицы `countries`\n" +
                "--\n" +
                "ALTER TABLE `countries`\n" +
                "  ADD PRIMARY KEY (`id`);\n" +
                "\n" +
                "--\n" +
                "-- Индексы таблицы `films`\n" +
                "--\n" +
                "ALTER TABLE `films`\n" +
                "  ADD PRIMARY KEY (`id`);\n" +
                "\n" +
                "--\n" +
                "-- Индексы таблицы `flyway_schema_history`\n" +
                "--\n" +
                "ALTER TABLE `flyway_schema_history`\n" +
                "  ADD PRIMARY KEY (`installed_rank`),\n" +
                "  ADD KEY `flyway_schema_history_s_idx` (`success`);\n" +
                "\n" +
                "--\n" +
                "-- Индексы таблицы `genres`\n" +
                "--\n" +
                "ALTER TABLE `genres`\n" +
                "  ADD PRIMARY KEY (`id`);\n" +
                "\n" +
                "--\n" +
                "-- Индексы таблицы `posts`\n" +
                "--\n" +
                "ALTER TABLE `posts`\n" +
                "  ADD PRIMARY KEY (`id`),\n" +
                "  ADD KEY `posts_users_fk` (`user_id`),\n" +
                "  ADD KEY `posts_films_fk` (`film_id`);\n" +
                "\n" +
                "--\n" +
                "-- Индексы таблицы `producing`\n" +
                "--\n" +
                "ALTER TABLE `producing`\n" +
                "  ADD PRIMARY KEY (`id`),\n" +
                "  ADD KEY `producing_users_fk` (`country_id`),\n" +
                "  ADD KEY `producing_films_fk` (`film_id`);\n" +
                "\n" +
                "--\n" +
                "-- Индексы таблицы `users`\n" +
                "--\n" +
                "ALTER TABLE `users`\n" +
                "  ADD PRIMARY KEY (`id`),\n" +
                "  ADD UNIQUE KEY `id` (`id`);\n" +
                "\n" +
                "--\n" +
                "-- AUTO_INCREMENT для сохранённых таблиц\n" +
                "--\n" +
                "\n" +
                "--\n" +
                "-- AUTO_INCREMENT для таблицы `authorities`\n" +
                "--\n" +
                "ALTER TABLE `authorities`\n" +
                "  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=108;\n" +
                "\n" +
                "--\n" +
                "-- AUTO_INCREMENT для таблицы `categorization`\n" +
                "--\n" +
                "ALTER TABLE `categorization`\n" +
                "  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;\n" +
                "\n" +
                "--\n" +
                "-- AUTO_INCREMENT для таблицы `countries`\n" +
                "--\n" +
                "ALTER TABLE `countries`\n" +
                "  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=94;\n" +
                "\n" +
                "--\n" +
                "-- AUTO_INCREMENT для таблицы `films`\n" +
                "--\n" +
                "ALTER TABLE `films`\n" +
                "  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;\n" +
                "\n" +
                "--\n" +
                "-- AUTO_INCREMENT для таблицы `genres`\n" +
                "--\n" +
                "ALTER TABLE `genres`\n" +
                "  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;\n" +
                "\n" +
                "--\n" +
                "-- AUTO_INCREMENT для таблицы `posts`\n" +
                "--\n" +
                "ALTER TABLE `posts`\n" +
                "  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;\n" +
                "\n" +
                "--\n" +
                "-- AUTO_INCREMENT для таблицы `producing`\n" +
                "--\n" +
                "ALTER TABLE `producing`\n" +
                "  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;\n" +
                "\n" +
                "--\n" +
                "-- AUTO_INCREMENT для таблицы `users`\n" +
                "--\n" +
                "ALTER TABLE `users`\n" +
                "  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=105;\n" +
                "\n" +
                "--\n" +
                "-- Ограничения внешнего ключа сохраненных таблиц\n" +
                "--\n" +
                "\n" +
                "--\n" +
                "-- Ограничения внешнего ключа таблицы `authorities`\n" +
                "--\n" +
                "ALTER TABLE `authorities`\n" +
                "  ADD CONSTRAINT `authorities_user_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;\n" +
                "\n" +
                "--\n" +
                "-- Ограничения внешнего ключа таблицы `categorization`\n" +
                "--\n" +
                "ALTER TABLE `categorization`\n" +
                "  ADD CONSTRAINT `categorization_countries_fk` FOREIGN KEY (`genre_id`) REFERENCES `genres` (`id`) ON DELETE CASCADE,\n" +
                "  ADD CONSTRAINT `categorization_films_fk` FOREIGN KEY (`film_id`) REFERENCES `films` (`id`) ON DELETE CASCADE;\n" +
                "\n" +
                "--\n" +
                "-- Ограничения внешнего ключа таблицы `posts`\n" +
                "--\n" +
                "ALTER TABLE `posts`\n" +
                "  ADD CONSTRAINT `posts_films_fk` FOREIGN KEY (`film_id`) REFERENCES `films` (`id`) ON DELETE CASCADE,\n" +
                "  ADD CONSTRAINT `posts_users_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;\n" +
                "\n" +
                "--\n" +
                "-- Ограничения внешнего ключа таблицы `producing`\n" +
                "--\n" +
                "ALTER TABLE `producing`\n" +
                "  ADD CONSTRAINT `producing_films_fk` FOREIGN KEY (`film_id`) REFERENCES `films` (`id`) ON DELETE CASCADE,\n" +
                "  ADD CONSTRAINT `producing_users_fk` FOREIGN KEY (`country_id`) REFERENCES `countries` (`id`) ON DELETE CASCADE;\n" +
                "COMMIT;\n" +
                "\n" +
                "/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;\n" +
                "/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;\n" +
                "/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;\n");
        writer.flush();
        writer.close();
    }
}
