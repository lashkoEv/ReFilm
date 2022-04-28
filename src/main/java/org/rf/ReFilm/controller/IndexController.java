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

//    @GetMapping("/admin")
//    public String logging(Model model) {
//        log.info(" --- index logging");
//        List<String> res = new ArrayList<>();
//        try {
//            File file = new File("D:/spring_log_file.log");
//            FileReader fr = new FileReader(file);
//            BufferedReader reader = new BufferedReader(fr);
//            StringBuilder sb = new StringBuilder();
//            String line = reader.readLine();
//            while (line != null) {
//                sb.append(" ");
//                sb.append(line);
//                line = reader.readLine();
//            }
//            Pattern pattern = Pattern.compile("(Logged user: )([A-Za-z0-9])*");
//            Matcher matcher = pattern.matcher(sb.toString());
//            while (matcher.find()) {
//                String str = sb.substring(matcher.start(), matcher.end());
//                str += " " + sb.substring(1,12);
//                res.add(str);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        model.addAttribute("strings", res);
//        return "admin";
//    }
}
