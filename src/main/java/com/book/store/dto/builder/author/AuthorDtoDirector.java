package com.book.store.dto.builder.author;

import com.book.store.model.Author;

public class AuthorDtoDirector {

    public  void  constructAuthor(AuthorBuilder authorBuilder, Author author) {
        authorBuilder.setId(author.getId());
        authorBuilder.setFirstName(author.getFirstname());
        authorBuilder.setLastName(author.getLastname());
    }
}
