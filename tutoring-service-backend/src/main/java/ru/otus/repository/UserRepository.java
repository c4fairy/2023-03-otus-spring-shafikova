package ru.otus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.domain.TutoringUser;

import java.util.UUID;

public interface UserRepository extends JpaRepository<TutoringUser, UUID> {
    TutoringUser findByUsername(String username);
}
