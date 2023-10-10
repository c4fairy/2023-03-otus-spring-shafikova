package ru.otus.mapper;

import org.springframework.security.core.GrantedAuthority;
import ru.otus.domain.Authority;

public interface AuthorityMapper {
    GrantedAuthority toGrantedAuthority(Authority authority);
}
