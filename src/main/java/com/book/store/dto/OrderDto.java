package com.book.store.dto;

import com.book.store.model.OrderItemDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Double price;
    private String shippingMethod;
    private Double shippingPrice;
    private String trackingNumber;
    private List<OrderItemDto> orderItem;
}
