package com.book.store.controller;

import com.book.store.dto.CategoryDto;
import com.book.store.dto.Converter;
import com.book.store.dto.ProductDto;
import com.book.store.model.Category;
import com.book.store.model.Product;
import com.book.store.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getCategories() {
        List<Category> categories = this.categoryService.getCategories();
        return new ResponseEntity<>(Converter.categoryListToCategoryListDto(categories), HttpStatus.OK);
    }

    @GetMapping("/category/products")
    public ResponseEntity<List<ProductDto>> getCategoryProducts(@RequestParam String categoryName) {
        List<Product> products = this.categoryService.getProductsByCategory(categoryName);
        return new ResponseEntity<>(Converter.productListToProductDtoList(products), HttpStatus.OK);
    }
}
