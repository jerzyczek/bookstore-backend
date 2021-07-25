package com.book.store.dto;

public class AuthorDto {
    private Long id;
    private String firstname;
    private String lastName;

    public AuthorDto(Long id, String firstname, String lastName) {
        this.id = id;
        this.firstname = firstname;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastName() {
        return lastName;
    }
}
