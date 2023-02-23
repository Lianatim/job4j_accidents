package ru.job4j.accidents.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.repository.AuthorityRepositoryJpa;
import ru.job4j.accidents.repository.UserRepositoryJpa;

@Controller
public class RegController {

    private final PasswordEncoder encoder;
    private final UserRepositoryJpa users;
    private final AuthorityRepositoryJpa authorities;

    public RegController(PasswordEncoder encoder, UserRepositoryJpa users, AuthorityRepositoryJpa authorities) {
        this.encoder = encoder;
        this.users = users;
        this.authorities = authorities;
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user) {
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(authorities.findByAuthority("ROLE_USER"));
        users.save(user);
        return "redirect:/login";
    }

    @GetMapping("/reg")
    public String regPage() {
        return "users/reg";
    }
}
