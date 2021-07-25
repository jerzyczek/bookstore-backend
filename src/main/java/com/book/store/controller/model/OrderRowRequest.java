package com.book.store.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderRowRequest {
    @JsonProperty("orderRowId")
    private Long orderRowId;
    @JsonProperty("productId")
    private Long productId;
    @JsonProperty("productQuantity")
    private int productQuantity;
}
