package org.rf.ReFilm.controller;

import lombok.extern.slf4j.Slf4j;
import org.rf.ReFilm.model.Post;
import org.rf.ReFilm.model.User;
import org.rf.ReFilm.service.CategoryService;
import org.rf.ReFilm.service.PostService;
import org.rf.ReFilm.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDate;

@Slf4j
@Controller
@RequestMapping("/posts")
public class PostController {

    private final UserService userService;

    private final PostService postService;

    private final CategoryService categoryService;

    public PostController(UserService userService, PostService postService, CategoryService categoryService) {
        this.userService = userService;
        this.postService = postService;
        this.categoryService = categoryService;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public String post(Model model, @PathVariable Long id) {
        log.info(" --- post " + id);
        Post post = postService.findById(id);
        if (post != null) {
            model.addAttribute("post", post);
        }
        return "post";
    }


    @ModelAttribute(name = "post")
    public Post getPost() {
        org.springframework.security.core.userdetails.User u = null;
        Post post = new Post();
        try {
            u = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            post.setUser(userService.findByUsername(u.getUsername()));
        } catch (Exception e) {
            log.error(" --- Error:", e);
        }
        return post;
    }


    @RolesAllowed({"ROLE_EXPERT", "ROLE_ADMIN"})
    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        log.info(" --- create post (get)");
        model.addAttribute("categories", categoryService.findAll());
        return "add-post";
    }

    @RolesAllowed({"ROLE_EXPERT", "ROLE_ADMIN"})
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute("post") @Validated Post post, BindingResult bindingResult, Model model,
                         RedirectAttributes redirectAttributes) {
        log.info(" --- create post (post)");
        if (bindingResult.hasErrors()) {
            log.info(" --- create post (post) bindingResult.hasErrors()");
            return "add-post";
        }
        try {
            post.setCreatedTs(LocalDate.now());
            log.info(" --- post {}", post);
            postService.save(post);
        } catch (Exception e) {
            log.error(" --- Error ", e);
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("post", post);
            return "add-post";
        }
        redirectAttributes.addFlashAttribute("msg", "Новий навчальний матеріал успушно додано!");
        redirectAttributes.addFlashAttribute("posts", postService.findAllByOrderByIdDesc());
        return "redirect:/";
    }


    @RolesAllowed({"ROLE_EXPERT", "ROLE_ADMIN"})
//   @PreAuthorize(value = "hasRole('ROLE_PSYCHOLOGIST') and #username == authentication.principal.username")
    @RequestMapping(path = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        log.info(" --- delete post (post)");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User u = null;
        try {
            u = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
            User user = userService.findByUsername(u.getUsername());
            User author = userService.findByUsername(postService.findById(id).getUser().getUsername());
            if (user.equals(author) || u.getAuthorities().toString().contains("ROLE_ADMIN")) {
                postService.delete(id);
                log.info(" --- deleted post id {}", id);
                redirectAttributes.addFlashAttribute("msg", "Навчальний матеріал видалено успішно!");
            }
        } catch (AuthenticationException e) {
            log.error(" --- Error ", e.getLocalizedMessage());
        }
        model.addAttribute("posts", postService.findAll());
        return "redirect:/";
    }

    @RolesAllowed({"ROLE_EXPERT", "ROLE_ADMIN"})
    @RequestMapping(path = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Model model) {
        log.info(" --- edit post (get)");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User u = null;
        try {
            u = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
            User user = userService.findByUsername(u.getUsername());
            User author = userService.findByUsername(postService.findById(id).getUser().getUsername());
            if (user.equals(author) || u.getAuthorities().toString().contains("ROLE_ADMIN")) {
                Post found = postService.findById(id);
                if (found != null) {
                    model.addAttribute("post", found);
                    model.addAttribute("categories", categoryService.findAll());
                    return "add-post";
                }
            }
        } catch (AuthenticationException e) {
            log.error(" --- Error ", e.getLocalizedMessage());
        }
        return "redirect:/";
    }

    @RolesAllowed({"ROLE_EXPERT", "ROLE_ADMIN"})
    @RequestMapping(path = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@ModelAttribute("post") @Validated Post post, BindingResult bindingResult, Model model,
                       RedirectAttributes redirectAttributes) {
        log.info(" --- edit post (post)");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User u = null;
        try {
            u = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
            User user = userService.findByUsername(u.getUsername());
            User author = userService.findByUsername(postService.findById(post.getId()).getUser().getUsername());
            if (user.equals(author) || u.getAuthorities().toString().contains("ROLE_ADMIN")) {
                if (bindingResult.hasErrors()) {
                    log.info(" --- edit post (post) bindingResult.hasErrors()");
                    return "add-post";
                }
                post.setUser(author);
                log.info(" --- post {}", post);
                postService.save(post);

            }
        } catch ( Exception e) {
            log.error(" --- Error ", e);
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("post", post);
            return "add-post";
        }
        redirectAttributes.addFlashAttribute("msg", "Навчальний матеріал відредаговано успішно!");
        model.addAttribute("posts", postService.findAllByOrderByIdDesc());
        return "redirect:/";
    }
    
}
