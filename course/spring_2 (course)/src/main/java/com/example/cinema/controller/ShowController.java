package com.example.cinema.controller;

import com.example.cinema.entity.Show;
import com.example.cinema.exceptions.custom.ShowNotFoundException;
import com.example.cinema.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@Validated
@RequestMapping("/show")
public class ShowController {

    private final ShowRepository showRepository;

    @Autowired
    public ShowController(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    @GetMapping("/all")
    public List<Show> getAllCurrentShows() {
        showRepository.saveAll(List.of(
                new Show(50, "Titanic"),
                new Show(80, "Terminator"),
                new Show(90, "Avatar")));

        return showRepository.findAll();
    }

    @GetMapping("/by_name")
    public Show getShowByMovieName(Model model,
                                   @RequestParam(name = "movieName") String movieName) {
        return showRepository.findByMovieName(movieName)
                .orElseThrow(() -> new ShowNotFoundException(movieName));
    }

   /* @PostMapping("/create")
    public void postNewShow(Model model,
                            @RequestParam(name = "movieName") String movieName,
                            @RequestParam(name = "minutes") int minutes) {
        showRepository.save(new Show(minutes, movieName));
    } */

    @DeleteMapping("/delete")
    public Show deleteSomeShow(Model model,
                                      @RequestParam(name = "movieName") String movieName) {

        return showRepository.deleteByMovieName(movieName)
                .orElseThrow(() -> new ShowNotFoundException(movieName));
    }

    @PutMapping("/change")
    public Show changeSomeShow(Model model,
                                         @RequestParam(name = "id") Long id,
                                         @RequestParam(name = "movieName") String movieName,
                                         @RequestParam(name = "minutes") int minutes) {
        Show newShow = new Show(minutes, movieName);

        return showRepository.findById(id)
                .map(show -> {
                    show.setMinutes(newShow.getMinutes());
                    show.setMovieName(newShow.getMovieName());
                    return showRepository.save(show);
                })
                .orElseGet(() -> {
                    newShow.setMovieName(movieName);
                    newShow.setMinutes(minutes);
                    newShow.setId(id);
                    return showRepository.save(newShow);
                });

    }

}
