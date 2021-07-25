package com.book.store.dto.builder.author;

import com.book.store.dto.AuthorDto;
import com.book.store.dto.builder.author.AuthorBuilder;

public class AuthorDtoBuilder implements AuthorBuilder {

    private Long id;
    private String firstname;
    private String lastname;

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstname = firstName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastname = lastName;
    }

    public AuthorDto build() {
        return new AuthorDto(id, firstname, lastname);
    }
}
