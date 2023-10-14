package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.otus.domain.TutoringUser;
import ru.otus.repository.UserRepository;
import ru.otus.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        TutoringUser user = userRepository.findByUsername(s);
        if (user == null) throw new UsernameNotFoundException("User not found");
        else return user;
    }
}
