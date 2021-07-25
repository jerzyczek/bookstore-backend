package com.book.store.model.factory;

import com.book.store.controller.model.CategoryRequest;
import com.book.store.exception.CategoryException;
import com.book.store.model.Category;

public class CategoryFactory extends AbstractCategoryFactory {

    @Override
    public Category createCategory(CategoryRequest categoryRequest) {
        validateCategoryRequest(categoryRequest);
        Category category = new Category();
        category.setName(categoryRequest.getName());

        return category;
    }

    private void validateCategoryRequest(CategoryRequest categoryRequest) {
        if (categoryRequest == null) {
            throw new CategoryException("Cant save category");
        }
    }
}
