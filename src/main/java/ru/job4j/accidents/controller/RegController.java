package ru.job4j.accidents.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.service.jpa.AuthorityServiceJpa;
import ru.job4j.accidents.service.jpa.UserServiceJpa;

import javax.validation.Valid;

@Controller
public class RegController {

    private final PasswordEncoder encoder;
    private final UserServiceJpa userService;
    private final AuthorityServiceJpa authorityService;

    public RegController(PasswordEncoder encoder, UserServiceJpa userService, AuthorityServiceJpa authorityService) {
        this.encoder = encoder;
        this.userService = userService;
        this.authorityService = authorityService;
    }

    @PostMapping("/reg")
    public String regSave(@Valid @ModelAttribute User user, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "users/reg";
        }
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(authorityService.findByAuthority("ROLE_USER"));
        try {
            userService.save(user);
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorMessage", "Пользователь с таким именем уже существует");
            return "users/reg";
        }
        return "redirect:/login";
    }

    @GetMapping("/reg")
    public String regPage(Model model) {
        model.addAttribute("user", new User());
        return "users/reg";
    }
}
