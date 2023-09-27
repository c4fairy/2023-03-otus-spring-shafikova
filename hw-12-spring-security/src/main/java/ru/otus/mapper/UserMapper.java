package ru.otus.mapper;

import org.springframework.security.core.userdetails.User;
import ru.otus.domain.LibraryUser;

public interface UserMapper {
    User toUserDetail(LibraryUser entity);
}
