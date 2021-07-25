package com.book.store.dto.builder.product;

import com.book.store.dto.Converter;
import com.book.store.model.Product;

public class ProductDtoDirector {

    public void constructProduct(ProductBuilder productBuilder, Product product) {
        productBuilder.setId(product.getId());
        productBuilder.seName(product.getName());
        productBuilder.setDescription(product.getDescription());
        productBuilder.setPrice(product.getPrice());
        productBuilder.setQuantity(product.getQuantity());
        productBuilder.setImageURL(product.getImgURL());
        productBuilder.setCategory(Converter.categoryToCategoryDto(product.getCategory()));
        productBuilder.setAuthor(Converter.authorToAuthorDto(product.getAuthor()));
    }

}
