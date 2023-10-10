package ru.otus.mapper.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import ru.otus.domain.LibraryUser;
import ru.otus.mapper.AuthorityMapper;
import ru.otus.mapper.UserMapper;

@Service
public class UserMapperImpl implements UserMapper {

    private final AuthorityMapper authorityMapper;

    public UserMapperImpl(AuthorityMapper authorityMapper) {
        this.authorityMapper = authorityMapper;
    }

    @Override
    public User toUserDetail(LibraryUser user) {
        return new User(user.getUsername(), user.getPassword(), user.isEnabled(),
                true, true, true,
                user.getAuthorities().stream().map(authorityMapper::toGrantedAuthority).toList());
    }
}
