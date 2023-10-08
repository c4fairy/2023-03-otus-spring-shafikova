package ru.otus.service;

import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String s);
}
