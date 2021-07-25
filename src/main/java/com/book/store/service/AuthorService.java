package com.book.store.service;

import com.book.store.controller.model.AuthorRequest;
import com.book.store.exception.AuthorException;
import com.book.store.model.Author;
import com.book.store.model.factory.author.AbstractAuthorFactory;
import com.book.store.model.factory.author.AuthorFactory;
import com.book.store.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author saveAuthor(AuthorRequest authorRequest) {
        AbstractAuthorFactory authorFactory = new AuthorFactory();

        return this.authorRepository.save(authorFactory.createAuthor(authorRequest));
    }

    public List<Author> getAuthors() {
        return this.authorRepository.findAll();
    }

    public Author getAuthor(final Long authorId) {
        Optional<Author> author = this.authorRepository.findById(authorId);

        if (!author.isPresent()) {
            throw new AuthorException("Cant find author by id");
        }

        return author.get();
    }

    public Author editAuthor(AuthorRequest authorRequest) {
        Long authorId = Optional.ofNullable(authorRequest.getId()).orElse(null);
        String firstname = Optional.ofNullable(authorRequest.getFirstname()).orElse(null);
        String lastname = Optional.ofNullable(authorRequest.getLastname()).orElse(null);

        if (authorId == null || firstname == null || lastname == null) {
            throw new AuthorException("Cant edit author");
        }

        Optional<Author> author = this.authorRepository.findById(authorId);

        if (!author.isPresent()) {
            throw new AuthorException("cant find author by id");
        }

        Author authorToSave = author.get();

        authorToSave.setFirstname(firstname);
        authorToSave.setLastname(lastname);

        return this.authorRepository.save(authorToSave);
    }

    public void deleteAuthorById(final Long authorId) {
        if (authorId == null) {
            throw new AuthorException("Cant delete author");
        }

        Optional<Author> author = this.authorRepository.findById(authorId);

        if (!author.isPresent()) {
            throw new AuthorException("Author dont exist");
        }

        this.authorRepository.delete(author.get());
    }

}
