package ru.otus.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, DELETED;

    @Override
    public String getAuthority() {
        return name();
    }
}
