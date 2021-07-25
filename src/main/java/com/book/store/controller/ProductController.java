package com.book.store.controller;

import com.book.store.controller.model.OrderRequest;
import com.book.store.dto.Converter;
import com.book.store.model.Product;
import com.book.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<?> getProducts() {
        List<Product> products = this.productService.getProducts();
        return new ResponseEntity<>(Converter.productListToProductDtoList(products), HttpStatus.OK);
    }

}
