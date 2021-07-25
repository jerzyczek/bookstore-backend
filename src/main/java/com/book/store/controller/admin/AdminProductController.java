package com.book.store.controller.admin;

import com.book.store.controller.model.ProductRequest;
import com.book.store.dto.Converter;
import com.book.store.dto.ProductDto;
import com.book.store.model.Product;
import com.book.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminProductController {

    private ProductService productService;

    @Autowired
    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/product")
    public ResponseEntity<ProductDto> saveProduct(@RequestBody @Valid ProductRequest productRequest) {
        Product product = this.productService.save(productRequest);
        return new ResponseEntity<>(Converter.productToProductDto(product), HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getProduct() {
        List<Product> products = this.productService.getProducts();
        return new ResponseEntity<>(Converter.productListToProductDtoList(products), HttpStatus.OK);
    }

    @GetMapping("/product")
    public ResponseEntity<ProductDto> getProductById(@RequestParam Long productId) {
        Product product = this.productService.getProductById(productId);
        return new ResponseEntity<>(Converter.productToProductDto(product), HttpStatus.OK);
    }

    @DeleteMapping("/product")
    public ResponseEntity<?> removeProduct(@RequestParam Long productId) {
        this.productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
