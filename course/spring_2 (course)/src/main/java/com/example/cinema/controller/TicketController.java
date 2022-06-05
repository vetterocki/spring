package com.example.cinema.controller;

import com.example.cinema.model.Ticket;
import com.example.cinema.security.details.UserDetailsImpl;
import com.example.cinema.service.TicketService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ticket")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/book")
    public String bookNewTicket(RedirectAttributes redirectAttributes,
                                @AuthenticationPrincipal UserDetailsImpl userDetails,
                                @RequestParam Long showId,
                                @RequestParam int row,
                                @RequestParam int number) {

        ticketService.save(showId, row, number, userDetails.getCurrentUser());
        redirectAttributes.addAttribute("id", showId);

        return "redirect:/show/seats";
    }

    @GetMapping("/all")
    public String getAllTicketsTable(Model model) {
        model.addAttribute("tickets", ticketService.findAll());
        return "bootstrap/tickets/all";
    }

    @GetMapping("/search")
    public String getAllAppropriateTickets(Model model, @RequestParam String str) {
        model.addAttribute("tickets", ticketService.findByString(str));
        return "bootstrap/tickets/all";
    }

    @GetMapping("/creation")
    public String getCreationForm() {
        return "bootstrap/tickets/creation";
    }

    @PostMapping("/create")
    public String createNewTicket(Model model,
                                  @RequestParam(name = "showId") Long showId,
                                  @RequestParam(name = "row") int row,
                                  @RequestParam(name = "number") int number,
                                  @RequestParam(name = "username") String username) {
        ticketService.save(showId, row, number, username);

        return getAllTicketsTable(model);
    }

    @GetMapping ("/delete")
    public String deleteSomeTicket(Model model, @RequestParam Long id) {
        ticketService.deleteById(id);
        return getAllTicketsTable(model);
    }

    @GetMapping("/changing")
    public String getChangingForm(Model model, @RequestParam Long id) {
        Ticket ticket = ticketService.findById(id);
        model.addAttribute("ticket", ticket);
        return "bootstrap/tickets/changing";
    }
    
    @PostMapping("/change")
    public String changeSomeTicket(Model model,
                                 @RequestParam(name = "id") Long id,
                                 @RequestParam(name = "showId") Long showId,
                                 @RequestParam(name = "row") int row,
                                 @RequestParam(name = "number") int number) {
        ticketService.change(id, showId, row, number);

        return getAllTicketsTable(model);

    }


}

