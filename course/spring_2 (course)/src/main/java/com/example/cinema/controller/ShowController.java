package com.example.cinema.controller;

import com.example.cinema.model.Show;
import com.example.cinema.service.SeatService;
import com.example.cinema.service.ShowService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/show")
public class ShowController {

    private final ShowService showService;
    private final SeatService seatService;

    public ShowController(ShowService showService, SeatService seatService) {
        this.showService = showService;
        this.seatService = seatService;
    }

    @GetMapping("/all")
    public String getAllShowTable(Model model) {
        model.addAttribute("shows", showService.findAll());
        return "bootstrap/shows/all";
    }

    @GetMapping("/search")
    public String getAllAppropriateShows(Model model, @RequestParam String str) {
        model.addAttribute("shows", showService.findByString(str));
        return "bootstrap/shows/all";
    }

    @GetMapping("/seats")
    public String getAllSeatsForTicket(Model model, @RequestParam Long id) {
        Show show = showService.findById(id);
        model.addAttribute("seats", seatService.findAllByShowId(id));
        model.addAttribute("show", show);

        return "bootstrap/shows/seats";
    }

    @GetMapping("/creation")
    public String getCreatingForm(Show show) {
        return "bootstrap/shows/creation";
    }

    @PostMapping("/create")
    public String createNewShow(@Valid @ModelAttribute("show") Show show,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "bootstrap/shows/creation";
        }
        showService.save(show);

        return getAllShowTable(model);
    }

    @GetMapping ("/delete")
    public String deleteSomeShow(Model model, @RequestParam Long id) {
        showService.deleteById(id);

        return getAllShowTable(model);

    }

    @GetMapping("/changing")
    public String getChangingForm(Show show) {
        return "bootstrap/shows/changing";
    }

    @PostMapping("/change")
    public String changeShow(@Valid @ModelAttribute("show") Show show,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "bootstrap/shows/changing";
        }

        showService.change(show);
        return getAllShowTable(model);
    }

}

