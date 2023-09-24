package ru.otus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.domain.LibraryUser;

public interface UserRepository extends JpaRepository<LibraryUser, Long> {
    LibraryUser findByUsername(String username);
}
