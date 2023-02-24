package ru.job4j.accidents.service.jpa;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Authority;
import ru.job4j.accidents.repository.jpa.AuthorityRepositoryJpa;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorityServiceJpa {

    private final AuthorityRepositoryJpa authorityRepository;

    public AuthorityServiceJpa(AuthorityRepositoryJpa authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public void save(Authority authority) {
        authorityRepository.save(authority);
    }


    public List<Authority> findAll() {
        return authorityRepository.findAll();
    }

    public Optional<Authority> findById(int id) {
        return authorityRepository.findById(id);
    }

    public Authority findByAuthority(String authority) {
        return authorityRepository.findByAuthority(authority);
    }

}
