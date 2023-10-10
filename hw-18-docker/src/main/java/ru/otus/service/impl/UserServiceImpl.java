package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import ru.otus.repository.LibraryUserRepository;
import ru.otus.mapper.UserMapper;
import ru.otus.service.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final LibraryUserRepository libraryUserRepository;
    private final UserMapper userMapper;

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.of(libraryUserRepository.findByUsername(username)).map(userMapper::toUserDetail);
    }
}