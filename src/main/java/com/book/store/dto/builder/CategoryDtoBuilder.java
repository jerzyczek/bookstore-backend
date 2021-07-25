package com.book.store.dto.builder;

import com.book.store.dto.CategoryDto;

public class CategoryDtoBuilder implements CategoryBuilder {

    private String name;

    @Override
    public void setName(String name) {
        this.name = name;
    }
    
    public CategoryDto build() {
        return new CategoryDto(name);
    }
}
