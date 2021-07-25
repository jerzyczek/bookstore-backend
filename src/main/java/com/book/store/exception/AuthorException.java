package com.book.store.exception;

public class AuthorException extends RuntimeException {

    public AuthorException(String message) {
        super(message);
    }

    public AuthorException(String message, Throwable cause) {
        super(message, cause);
    }
}
