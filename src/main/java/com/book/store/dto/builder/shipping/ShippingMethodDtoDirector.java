package com.book.store.dto.builder.shipping;

public class ShippingMethodDtoDirector {

    public void constructShippingMethod(ShippingBuilder shippingBuilder, String name, Double price) {
        shippingBuilder.setName(name);
        shippingBuilder.setPrice(price);
    }

}
