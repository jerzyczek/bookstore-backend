package com.book.store.model.singleton;

public class DhlSingleton {
    private final String NAME = "Dhl";
    private final double PRICE = 14;

    private static DhlSingleton instance;

    public static DhlSingleton getInstance() {
        if (instance == null) {
            instance = new DhlSingleton();
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
