package com.book.store.service.shipping;

import com.book.store.model.singleton.DhlSingleton;

import java.util.Locale;
import java.util.Random;

public class DhlStrategy implements ShippingStrategy {
    @Override
    public String generateTrackingNumber() {
        DhlSingleton dhlSingleton = DhlSingleton.getInstance();
        return (dhlSingleton.getName()+generateRandomValues()).toUpperCase(Locale.ROOT);
    }

    private int generateRandomValues() {
        return new Random().nextInt(10);
    }
}
