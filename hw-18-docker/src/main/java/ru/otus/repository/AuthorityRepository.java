package ru.otus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.domain.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
