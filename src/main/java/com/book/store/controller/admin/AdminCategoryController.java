package com.book.store.controller.admin;

import com.book.store.controller.model.CategoryRequest;
import com.book.store.dto.CategoryDto;
import com.book.store.dto.Converter;
import com.book.store.model.Category;
import com.book.store.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminCategoryController {

    private CategoryService categoryService;

    @Autowired
    public AdminCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/category")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody @Valid CategoryRequest categoryRequest) {
        Category category = this.categoryService.save(categoryRequest);
        return new ResponseEntity<>(Converter.categoryToCategoryDto(category), HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getCategories() {
        List<Category> categories = this.categoryService.getCategories();
        return new ResponseEntity<>(Converter.categoryListToCategoryListDto(categories), HttpStatus.OK);
    }

    @PutMapping("/category")
    public ResponseEntity<CategoryDto> editCategory(@RequestBody @Valid CategoryRequest categoryRequest) {
        Category category = this.categoryService.edit(categoryRequest);
        return new ResponseEntity<>(Converter.categoryToCategoryDto(category), HttpStatus.OK);
    }

    @DeleteMapping("/category")
    public ResponseEntity<?> deleteCategory(@RequestParam String categoryName) {
        this.categoryService.deleteCategory(categoryName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
