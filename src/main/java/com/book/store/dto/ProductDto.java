package com.book.store.dto;

public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private int quantity;
    private String imgURL;
    private CategoryDto category;
    private AuthorDto author;

    public ProductDto(Long id,
                      String name,
                      String description,
                      Double price,
                      int quantity,
                      String imgURL,
                      CategoryDto category,
                      AuthorDto author) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.imgURL = imgURL;
        this.category = category;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getImgURL() {
        return imgURL;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public AuthorDto getAuthor() {
        return author;
    }
}
