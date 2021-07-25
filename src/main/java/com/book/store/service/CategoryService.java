package com.book.store.service;

import com.book.store.controller.model.CategoryRequest;
import com.book.store.controller.model.CategoryUpdateRequest;
import com.book.store.exception.CategoryException;
import com.book.store.exception.ProductException;
import com.book.store.model.Category;
import com.book.store.model.Product;
import com.book.store.model.factory.AbstractCategoryFactory;
import com.book.store.model.factory.CategoryFactory;
import com.book.store.repository.CategoryRepository;
import com.book.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    private ProductRepository productRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository,
                           ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public Category getCategory(final String categoryName) {
        Optional<Category> category = this.categoryRepository.findByName(categoryName);

        if (!category.isPresent()) {
            throw new CategoryException("Cant find category by name");
        }

        return category.get();
    }

    public Category save(CategoryRequest categoryRequest) {
        AbstractCategoryFactory abstractCategoryFactory = new CategoryFactory();

        return this.categoryRepository.save(abstractCategoryFactory.createCategory(categoryRequest));
    }

    public List<Category> getCategories() {
        return this.categoryRepository.findAll();
    }

    public Category edit(CategoryUpdateRequest categoryRequest) {
        String categoryName = Optional.ofNullable(categoryRequest.getOldName()).orElse(null);

        if (categoryName == null) {
            throw new CategoryException("Can't find category");
        }

        Optional<Category> category = this.categoryRepository.findByName(categoryName);

        if (!category.isPresent()) {
            throw new CategoryException("Can't find category");
        }

        Category categoryToSave = category.get();

        categoryToSave.setName(categoryRequest.getNewName());

        return this.categoryRepository.save(categoryToSave);
    }

    public void deleteCategory(final String categoryName) {
        String categorybyName = Optional.ofNullable(categoryName).orElse(null);

        if (categorybyName == null) {
            throw new CategoryException("Category name cant be null");
        }

        Optional<Category> category = this.categoryRepository.findByName(categorybyName);

        if (!category.isPresent()) {
            throw new CategoryException("Cant find category");
        }

        this.categoryRepository.delete(category.get());
    }

    public List<Product> getProductsByCategory(final String categoryName) {

        if (categoryName == null) {
            throw new CategoryException("Category name cant be null");
        }

        Optional<Category> category = this.categoryRepository.findByName(categoryName);

        if (!category.isPresent()) {
            throw new CategoryException("Can't find category");
        }

        Long categoryId = category.get().getId();

        Optional<List<Product>> products = this.productRepository.findByCategoryId(categoryId);

        if (!products.isPresent()) {
            throw new ProductException("Cant find products by category name");
        }

        return products.get();
    }

}
