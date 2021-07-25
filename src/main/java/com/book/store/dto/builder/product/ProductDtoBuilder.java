package com.book.store.dto.builder.product;

import com.book.store.dto.AuthorDto;
import com.book.store.dto.CategoryDto;
import com.book.store.dto.ProductDto;

public class ProductDtoBuilder implements ProductBuilder {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private int quantity;
    private String imageURL;
    private CategoryDto categoryDto;
    private AuthorDto authorDto;

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void seName(String name) {
        this.name = name;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public void setCategory(CategoryDto category) {
        this.categoryDto = category;
    }

    @Override
    public void setAuthor(AuthorDto author) {
        this.authorDto = author;
    }

    public ProductDto build() {
        return new ProductDto(id, name, description, price, quantity, imageURL, categoryDto, authorDto);
    }
}
