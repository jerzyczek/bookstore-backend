package com.book.store.controller.model;

import lombok.Data;

@Data
public class AuthorRequest {
    private Long id;
    private String firstname;
    private String lastname;
}
