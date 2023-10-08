package ru.otus.mapper.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import ru.otus.domain.Authority;
import ru.otus.mapper.AuthorityMapper;

@Service
public class AuthorityMapperImpl implements AuthorityMapper {
    @Override
    public GrantedAuthority toGrantedAuthority(Authority authority) {
        return new SimpleGrantedAuthority(authority.getAuthorityName());
    }
}
