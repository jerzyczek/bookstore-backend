package com.book.store.dto;

public class ShippingMethodDto {
    private String name;
    private Double price;

    public ShippingMethodDto(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }
}
