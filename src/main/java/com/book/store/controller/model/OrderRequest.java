package com.book.store.controller.model;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private String shippingMethod;
    List<OrderRowRequest> orderRowRequest;
}
