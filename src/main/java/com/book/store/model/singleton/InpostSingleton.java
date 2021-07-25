package com.book.store.model.singleton;

public class InpostSingleton {
    private final String NAME = "Inpost";
    private final double PRICE = 15;

    private static InpostSingleton instance;

    public static InpostSingleton getInstance() {
        if (instance == null) {
            instance = new InpostSingleton();
        }

        return instance;
    }

    public String getName() {
        return instance.NAME;
    }

    public double getPrice() {
        return instance.PRICE;
    }
}
