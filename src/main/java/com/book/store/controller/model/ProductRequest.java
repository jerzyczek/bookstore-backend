package com.book.store.controller.model;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private String description;
    private Double price;
    private int quantity;
    private String imgURL;
    private CategoryRequest category;
    private AuthorRequest author;
}
