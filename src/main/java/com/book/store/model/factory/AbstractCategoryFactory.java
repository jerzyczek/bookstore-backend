package com.book.store.model.factory;

import com.book.store.controller.model.CategoryRequest;
import com.book.store.model.Category;

public abstract class AbstractCategoryFactory {

    public abstract Category createCategory(CategoryRequest categoryRequest);
}
