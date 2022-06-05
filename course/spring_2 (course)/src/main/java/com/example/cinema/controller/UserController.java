package com.example.cinema.controller;

import com.example.cinema.exceptions.authorization.UserAlreadyExistsException;
import com.example.cinema.security.details.UserDetailsImpl;
import com.example.cinema.security.model.User;
import com.example.cinema.service.TicketService;
import com.example.cinema.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller

public class UserController {

    private final UserService userService;
    private final TicketService ticketService;

    public UserController(UserService userService, TicketService ticketService) {
        this.userService = userService;
        this.ticketService = ticketService;
    }

    @GetMapping("/form")
    public String getRegistrationForm(User user) {
        return "bootstrap/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "bootstrap/register";
        } else if (userService.isValid(user)) {
            userService.save(user);
        } else {
            UserAlreadyExistsException exception = new UserAlreadyExistsException(user.getUsername());
            model.addAttribute("ex", exception);
            return "bootstrap/register";
        }

        return "bootstrap/home";

    }

    @GetMapping("/account")
    public String getAccountPage(@AuthenticationPrincipal UserDetailsImpl user, Model model) {
        model.addAttribute("tickets", ticketService.findAllByUserId(user.getId()));
        return "bootstrap/users/account";
    }


}
