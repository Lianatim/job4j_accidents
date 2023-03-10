package ru.job4j.accidents.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accidents.service.jpa.AccidentServiceJpa;

@Controller
public class IndexController {

    private final AccidentServiceJpa accidentService;

    public IndexController(AccidentServiceJpa accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("accidents", accidentService.findAll());
        return "index";
    }
}
