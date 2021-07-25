package com.book.store.service;

import com.book.store.controller.model.ProductRequest;
import com.book.store.exception.AuthorException;
import com.book.store.exception.CategoryException;
import com.book.store.exception.ProductException;
import com.book.store.model.Author;
import com.book.store.model.Category;
import com.book.store.model.Product;
import com.book.store.model.factory.product.AbstractProductFactory;
import com.book.store.model.factory.product.ProductFactory;
import com.book.store.repository.AuthorRepository;
import com.book.store.repository.CategoryRepository;
import com.book.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    private CategoryRepository categoryRepository;

    private AuthorRepository authorRepository;

    @Autowired
    public ProductService(ProductRepository productRepository,
                          CategoryRepository categoryRepository,
                          AuthorRepository authorRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.authorRepository = authorRepository;
    }

    public Product save(ProductRequest productRequest) {

        Optional<Category> category = this.categoryRepository.findByName(productRequest.getCategory().getName());

        if (!category.isPresent()) {
            throw new CategoryException("Can't find category");
        }

        Optional<Author> author = this.authorRepository.findById(productRequest.getAuthor().getId());

        if (!author.isPresent()) {
            throw new AuthorException("Can't find author");
        }

        AbstractProductFactory abstractProductFactory = new ProductFactory();
        return this.productRepository.save(abstractProductFactory.createProduct(productRequest, author.get(), category.get()));
    }

    public List<Product> getProducts() {
        return this.productRepository.findAll();
    }

    public void deleteProduct(final Long productId) {
        if (productId == null) {
            throw new ProductException("Product cant be null");
        }

        Optional<Product> product = this.productRepository.findById(productId);

        if (!product.isPresent()) {
            throw new ProductException("Cant find product");
        }

        this.productRepository.delete(product.get());
    }

    public Product getProductById(final Long productId) {
        if (productId == null) {
            throw new ProductException("Product id cant be null");
        }

        Optional<Product> product = this.productRepository.findById(productId);

        if (!product.isPresent()) {
            throw new ProductException("Cant find product");
        }

        return product.get();
    }

}
