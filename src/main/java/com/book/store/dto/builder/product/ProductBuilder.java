package com.book.store.dto.builder.product;

import com.book.store.dto.AuthorDto;
import com.book.store.dto.CategoryDto;

public interface ProductBuilder {
    void setId(Long id);
    void seName(String name);
    void setDescription(String description);
    void setPrice(Double price);
    void setQuantity(int quantity);
    void setImageURL(String imageURL);
    void setCategory(CategoryDto category);
    void setAuthor(AuthorDto author);
}
