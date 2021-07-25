package com.book.store.model.factory.product;

import com.book.store.controller.model.ProductRequest;
import com.book.store.model.Author;
import com.book.store.model.Category;
import com.book.store.model.Product;

public abstract class AbstractProductFactory {

    public abstract Product createProduct(ProductRequest productRequest, Author author, Category category);

}
