package com.book.store.model.factory.product;

import com.book.store.controller.model.ProductRequest;
import com.book.store.exception.ProductException;
import com.book.store.model.Author;
import com.book.store.model.Category;
import com.book.store.model.Product;
import com.book.store.repository.AuthorRepository;
import com.book.store.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductFactory extends AbstractProductFactory {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Product createProduct(ProductRequest productRequest, Author author, Category category) {
        validateProduct(productRequest);

        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setQuantity(productRequest.getQuantity());
        product.setImgURL(productRequest.getImgURL());
        product.setAuthor(author);
        product.setCategory(category);
        return product;
    }

    private void validateProduct(ProductRequest productRequest) {
        if (checkIfProductRequestIsNull(productRequest) || checkIfProductRequestIsEmpty(productRequest)) {
            throw new ProductException("Can't save product");
        }
    }

    private boolean checkIfProductRequestIsNull(ProductRequest productRequest) {
        if (productRequest == null) {
            return true;
        } else if (productRequest.getName() == null) {
            return true;
        } else if (productRequest.getDescription() == null) {
            return true;
        } else if (productRequest.getPrice() == null) {
            return true;
        } else if (productRequest.getImgURL() == null ) {
            return true;
        } else if (productRequest.getCategory() == null) {
            return true;
        } else if (productRequest.getAuthor() == null) {
            return true;
        }

        return false;
    }

    private boolean checkIfProductRequestIsEmpty(ProductRequest productRequest) {
        if (productRequest.getName().isEmpty()) {
            return true;
        } else if (productRequest.getDescription().isEmpty()) {
            return true;
        } else if (productRequest.getQuantity() == 0) {
            return true;
        } else if (productRequest.getPrice() == 0.00) {
            return true;
        } else if (productRequest.getImgURL().isEmpty()) {
            return true;
        } else if (productRequest.getCategory().getName().isEmpty()) {
            return true;
        }

        return false;
    }
}
