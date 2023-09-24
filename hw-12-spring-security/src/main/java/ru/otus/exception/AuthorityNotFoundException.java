package ru.otus.exception;

import javax.naming.AuthenticationException;

public class AuthorityNotFoundException extends AuthenticationException {
    public AuthorityNotFoundException(String msg) {
        super(msg);
    }
}