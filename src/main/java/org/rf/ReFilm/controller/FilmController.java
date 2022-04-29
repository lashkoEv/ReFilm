package org.rf.ReFilm.controller;

import lombok.extern.slf4j.Slf4j;
import org.rf.ReFilm.model.Film;
import org.rf.ReFilm.model.Post;
import org.rf.ReFilm.service.*;
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
@RequestMapping("/films")
public class FilmController {
    private final FilmService filmService;

    private final PostService postService;

    private final GenreService genreService;

    private final CategorizationService categorizationService;

    private final CountryService countryService;

    private final ProducingService producingService;

    public FilmController(FilmService filmService, PostService postService, GenreService genreService,
                          CategorizationService categorizationService, CountryService countryService, ProducingService producingService) {
        this.filmService = filmService;
        this.postService = postService;
        this.genreService = genreService;
        this.categorizationService = categorizationService;
        this.countryService = countryService;
        this.producingService = producingService;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public String film(Model model, @PathVariable Long id, Integer page, Integer size) {
        log.info(" --- film " + id);
        Film film = filmService.findById(id);
        List<Post> posts = postService.findAllByFilmId(film.getId());
        model.addAttribute("film", film);
        model.addAttribute("posts", posts);
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("genresFilms", categorizationService.findByFilm(film));
        return "film";
    }

    @RequestMapping(path = "/genres", method = RequestMethod.GET)
    public String filmGenres(Model model) {
        model.addAttribute("genres", genreService.findAll());
        return "genres";
    }

    @RequestMapping(path = "/genres", method = RequestMethod.POST)
    public String filmGenre(Model model, @Param("genres") String genres) {
        log.info(" --- film genres " + genres);
        String[] ids = genres.split(",");
        model.addAttribute("films", filmService.findByGenreIds(ids));
        return "films";
    }

    @RequestMapping(path = "/countries", method = RequestMethod.GET)
    public String filmCountries(Model model) {
        model.addAttribute("countries", countryService.findAll());
        return "countries";
    }

    @RequestMapping(path = "/countries", method = RequestMethod.POST)
    public String filmCuontry(Model model, @Param("countries") String countries) {
        log.info(" --- film countries " + countries);
        String[] ids = countries.split(",");
        model.addAttribute("films", filmService.findByCountryIds(ids));
        return "films";
    }


    @RequestMapping(path = "", method = RequestMethod.GET)
    public String films(Model model, Integer page, Integer size, @Param("search") String search) {
        log.info(" --- film index");
        Pageable pageable = PageRequest.of(page == null ? 0 : page, size == null ? 5 : size, Sort.by("id").descending());
        Page<Film> filmPage;
        if (search != null) {
            filmPage = filmService.findSearchedFilms(search, pageable);
        } else {
            filmPage = filmService.findAll(pageable);
        }
        model.addAttribute("films", filmPage.getContent());
        model.addAttribute("page", filmPage);
        return "films";
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        log.info(" --- create film (get)");
        model.addAttribute("newFilm", new Film());
        return "add-film";
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute("newFilm") @Validated Film newFilm, BindingResult bindingResult, Model model,
                         RedirectAttributes redirectAttributes) {
        log.info(" --- add film");
        if (bindingResult.hasErrors()) {
            log.info(" --- create film (post) bindingResult.hasErrors()");
            return "add-film";
        }
        try {
            filmService.save(newFilm);
        } catch (Exception e) {
            log.error(" --- Error ", e);
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("film", newFilm);
            return "add-film";
        }
        redirectAttributes.addFlashAttribute("msg", "Запис про фільм створено успішно!");
        redirectAttributes.addFlashAttribute("films", filmService.findAll());
        return "redirect:/films";
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @RequestMapping(path = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        log.info(" --- delete film");
        try {
            System.out.println(id);
            filmService.delete(id);
            log.info(" --- deleted film id {}", id);

        } catch (Exception e) {
            log.error(" --- Error " + e.getLocalizedMessage());
        }
        redirectAttributes.addFlashAttribute("msg", "Запис про фільм видалено успішно!");
        redirectAttributes.addFlashAttribute("films", filmService.findAll());
        return "redirect:/films";
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @RequestMapping(path = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Model model) {
        log.info(" --- edit film (get)");
        Film found = filmService.findById(id);
        if (found != null) {
            model.addAttribute("newFilm", found);
            return "add-film";
        }
        return "redirect:/";
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @RequestMapping(path = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@ModelAttribute("newFilm") @Validated Film film, BindingResult bindingResult, Model model,
                       RedirectAttributes redirectAttributes) {
        log.info(" --- edit film (post)");

        if (bindingResult.hasErrors()) {
            log.info(" --- edit film (post) bindingResult.hasErrors()");
            return "add-film";
        } else {
            try {
                filmService.save(film);
            } catch (Exception e) {
                log.error(" --- Error ", e);
                model.addAttribute("errorMessage", e.getMessage());
                model.addAttribute("newFilm", film);
                return "add-film";
            }
        }
        redirectAttributes.addFlashAttribute("msg", "Запис про фільм змінено успішно!");
        redirectAttributes.addFlashAttribute("films", filmService.findAll());
        return "redirect:/films";
    }
}
