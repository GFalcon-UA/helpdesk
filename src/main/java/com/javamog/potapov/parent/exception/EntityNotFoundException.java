package com.javamog.potapov.parent.exception;

import org.springframework.dao.EmptyResultDataAccessException;

public class EntityNotFoundException extends EmptyResultDataAccessException {
    public EntityNotFoundException(Long id) {
        this(String.format("Entity with id=%s does not exist", id));
    }

    public EntityNotFoundException(String message) {
        super(message, 1);
    }

    public EntityNotFoundException(String message, Throwable ex) {
        super(message, 1, ex);
    }

    public EntityNotFoundException(Long id, Throwable ex) {
        this(String.format("Entity with id=%s does not exist", id), ex);
    }
}