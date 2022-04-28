package org.rf.ReFilm.controller;

import lombok.extern.slf4j.Slf4j;
import org.rf.ReFilm.model.Post;
import org.rf.ReFilm.model.User;
import org.rf.ReFilm.model.Authority;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.security.RolesAllowed;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Controller
@RequestMapping("/experts")
public class ExpertController {

    private final UserService userService;

    private final PostService postService;

    private final PasswordEncoder passwordEncoder;

    public ExpertController(UserService userService, PostService postService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.postService = postService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public String expert(Model model, @PathVariable Long id, Integer page, Integer size) {
        log.info(" --- expert " + id);
        User user = userService.findById(id);
        List<Post> posts = postService.findAllByUserId(id);
        model.addAttribute("user", user);
        model.addAttribute("posts", posts);
        return "expert";
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public String experts(Model model, Integer page, Integer size, @Param("search") String search) {
        log.info(" --- expert index");
        Pageable pageable = PageRequest.of(page == null ? 0 : page, size == null ? 2 : size, Sort.by("id").descending());
        Page<User> userPage;
        if (search != null) {
            userPage = userService.findSearchedUsers(search, pageable);
        } else {
            userPage = userService.findUsersByAuthoritiesNative(pageable);
        }
        model.addAttribute("users", userPage.getContent());
        model.addAttribute("page", userPage);
        return "experts";
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        log.info(" --- create user (get)");
        model.addAttribute("user", new User());
        return "add-user";
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute("user") @Validated User user, BindingResult bindingResult, Model model,
                         RedirectAttributes redirectAttributes) {
        log.info(" --- register");

        if (bindingResult.hasErrors()) {
            log.info(" --- create user (post) bindingResult.hasErrors()");
            return "add-user";
        }

        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            Set<Authority> authoritySet = new HashSet<>();
            Authority authority = new Authority();
            authority.setAuthority("ROLE_EXPERT");
            authority.setUser(user);
            authoritySet.add(authority);
            user.setAuthorities(authoritySet);
            user.setEnabled(1);
            userService.save(user);
        } catch (Exception e) {
            log.error(" --- Error ", e);
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("user", user);
            return "add-user";
        }
        redirectAttributes.addFlashAttribute("msg", "Новий обліковий запис експерта успішно створено!");
        redirectAttributes.addFlashAttribute("users", userService.findAll());
        return "redirect:/experts";
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @RequestMapping(path = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        log.info(" --- delete user");
        try {
            userService.delete(id);
            log.info(" --- deleted user id {}", id);
        } catch (Exception e) {
            log.error(" --- Error ", e.getLocalizedMessage());
        }
        redirectAttributes.addFlashAttribute("msg", "Обліковий запис експерта успішно видалено!");
        redirectAttributes.addFlashAttribute("users", userService.findAll());
        return "redirect:/experts";
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @RequestMapping(path = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Model model) {
        log.info(" --- edit user (get)");
        User found = userService.findById(id);
        if (found != null) {
            model.addAttribute("user", found);
            return "add-user";
        }
        return "redirect:/";
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @RequestMapping(path = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@ModelAttribute("user") @Validated User user, BindingResult bindingResult, Model model,
                       RedirectAttributes redirectAttributes) {
        log.info(" --- edit user (post)");

            if (bindingResult.hasErrors()) {
                log.info(" --- create user (post) bindingResult.hasErrors()");
                return "add-user";
            } else {
                try {
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    Set<Authority> authoritySet = new HashSet<>();
                    Authority authority = new Authority();
                    authority.setAuthority("ROLE_EXPERT");
                    authority.setUser(user);
                    authoritySet.add(authority);
                    user.setAuthorities(authoritySet);
                    user.setEnabled(1);
                    userService.save(user);
                } catch (Exception e) {
                    log.error(" --- Error ", e);
                    model.addAttribute("errorMessage", e.getMessage());
                    model.addAttribute("user", user);
                    return "add-user";
                }
            }
        redirectAttributes.addFlashAttribute("msg", "Обліковий запис експерта успішно змінено!");
        redirectAttributes.addFlashAttribute("users", userService.findAll());
        return "redirect:/experts";
    }
}
