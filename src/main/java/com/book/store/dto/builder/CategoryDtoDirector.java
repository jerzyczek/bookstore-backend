package com.book.store.dto.builder;

import com.book.store.model.Category;

public class CategoryDtoDirector {

    public void constructCategory(CategoryBuilder categoryBuilder, Category category) {
        categoryBuilder.setName(category.getName());
    }
}
