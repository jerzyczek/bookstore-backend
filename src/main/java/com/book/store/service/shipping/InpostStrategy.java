package com.book.store.service.shipping;

import com.book.store.model.singleton.InpostSingleton;

import java.util.Locale;
import java.util.Random;

public class InpostStrategy implements ShippingStrategy {

    @Override
    public String generateTrackingNumber() {
        InpostSingleton inpostSingleton = InpostSingleton.getInstance();
        return (inpostSingleton.getName()+generateRandomValues()).toUpperCase(Locale.ROOT);
    }

    private int generateRandomValues() {
        return new Random().nextInt(10);
    }
}
