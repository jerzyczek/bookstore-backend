package com.book.store.model.factory.author;

import com.book.store.controller.model.AuthorRequest;
import com.book.store.exception.AuthorException;
import com.book.store.model.Author;

public class AuthorFactory extends AbstractAuthorFactory {

    @Override
    public Author createAuthor(AuthorRequest authorRequest) {
        validateAuthorRequestData(authorRequest);
        Author author = new Author();
        author.setFirstname(authorRequest.getFirstname());
        author.setLastname(authorRequest.getLastname());

        return author;
    }

    private void validateAuthorRequestData(final AuthorRequest authorRequest) {
        if (checkIfAuthorRequestIsNull(authorRequest) || checkIfAuthorRequestIsEmpty(authorRequest)) {
            throw new AuthorException("Cant save author");
        }
    }

    private boolean checkIfAuthorRequestIsNull(final AuthorRequest authorRequest) {
        if (authorRequest == null) {
            return true;
        } else if (authorRequest.getLastname() == null) {
            return true;
        } else if (authorRequest.getFirstname() == null) {
            return true;
        }

        return false;
    }

    private boolean checkIfAuthorRequestIsEmpty(final AuthorRequest authorRequest) {
        if (authorRequest.getFirstname().isEmpty()) {
            return true;
        } else if (authorRequest.getLastname().isEmpty()) {
            return true;
        }

        return false;
    }

}
