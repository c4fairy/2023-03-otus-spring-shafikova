package ru.otus.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;


@Entity
@Data
@Table(name = "users")
public class LibraryUser {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @OneToMany(cascade = ALL, orphanRemoval = true, mappedBy = "user", fetch = FetchType.EAGER)
    private List<Authority> authorities;
}
