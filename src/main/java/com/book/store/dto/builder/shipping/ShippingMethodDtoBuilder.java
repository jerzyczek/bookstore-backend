package com.book.store.dto.builder.shipping;

import com.book.store.dto.ShippingMethodDto;

public class ShippingMethodDtoBuilder implements ShippingBuilder {

    private String name;
    private Double price;

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setPrice(Double price) {
        this.price = price;
    }

    public ShippingMethodDto build() {
        return new ShippingMethodDto(name, price);
    }
}
