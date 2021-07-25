package com.book.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRowDto {
    private Long orderRowId;
    private Long productId;
    private ProductDto productDto;
    private int productQuantity;
}
