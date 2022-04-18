package org.rf.ReFilm.controller;

import lombok.extern.slf4j.Slf4j;
import org.rf.ReFilm.model.Category;
import org.rf.ReFilm.model.Post;
import org.rf.ReFilm.service.CategoryService;
import org.rf.ReFilm.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
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
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    private final PostService postService;

    public CategoryController(CategoryService categoryService, PostService postService) {
        this.categoryService = categoryService;
        this.postService = postService;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public String category(Model model, @PathVariable Long id, Integer page, Integer size) {
        log.info(" --- category " + id);
        Category category = categoryService.findById(id);
       // List<Post> posts = postService.findAllByCategoryId(category.getId());
        model.addAttribute("category", category);
       // model.addAttribute("posts", posts);
        return "category";
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public String categories(Model model, Integer page, Integer size, @Param("search") String search) {
        log.info(" --- category index");
        Pageable pageable = PageRequest.of(page == null ? 0 : page, size == null ? 5 : size, Sort.by("id").descending());
        Page<Category> categoryPage;
        if (search != null) {
            categoryPage = categoryService.findSearchedCategories(search, pageable);
        } else {
            categoryPage = categoryService.findAll(pageable);
        }
        model.addAttribute("categories", categoryPage.getContent());
        model.addAttribute("page", categoryPage);
        return "categories";
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        log.info(" --- create category (get)");
        model.addAttribute("newCategory", new Category());
        return "add-category";
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute("newCategory") @Validated Category newCategory, BindingResult bindingResult, Model model,
                         RedirectAttributes redirectAttributes) {
        log.info(" --- add category");
        if (bindingResult.hasErrors()) {
            log.info(" --- create category (post) bindingResult.hasErrors()");
            return "add-category";
        }
        try {
            categoryService.save(newCategory);
        } catch (Exception e) {
            log.error(" --- Error ", e);
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("category", newCategory);
            return "add-category";
        }
        redirectAttributes.addFlashAttribute("msg", "Категорія створена успішно!");
        redirectAttributes.addFlashAttribute("categories", categoryService.findAll());
        return "redirect:/categories";
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @RequestMapping(path = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        log.info(" --- delete category");
        try {
            System.out.println(id);
            categoryService.delete(id);
            log.info(" --- deleted category id {}", id);

        } catch (Exception e) {
            log.error(" --- Error ", e.getLocalizedMessage());
        }
        redirectAttributes.addFlashAttribute("msg", "Категорія видалена успішно!");
        redirectAttributes.addFlashAttribute("categories", categoryService.findAll());
        return "redirect:/categories";
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @RequestMapping(path = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Model model) {
        log.info(" --- edit category (get)");
        Category found = categoryService.findById(id);
        if (found != null) {
            model.addAttribute("newCategory", found);
            return "add-category";
        }
        return "redirect:/";
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @RequestMapping(path = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@ModelAttribute("newCategory") @Validated Category category, BindingResult bindingResult, Model model,
                       RedirectAttributes redirectAttributes) {
        log.info(" --- edit category (post)");

        if (bindingResult.hasErrors()) {
            log.info(" --- edit category (post) bindingResult.hasErrors()");
            return "add-category";
        } else {
            try {
               categoryService.save(category);
            } catch (Exception e) {
                log.error(" --- Error ", e);
                model.addAttribute("errorMessage", e.getMessage());
                model.addAttribute("newCategory", category);
                return "add-category";
            }
        }
        redirectAttributes.addFlashAttribute("msg", "Категорія змінена успішно!");
        redirectAttributes.addFlashAttribute("categories", categoryService.findAll());
        return "redirect:/categories";
    }
}
