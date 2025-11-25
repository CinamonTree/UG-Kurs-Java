package com.stockmarket.domain;

public abstract class Asset {

    protected double price;

    public Asset(double price) {
        this.price = validatePrice(price);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = validatePrice(price);
    }

    public abstract double calculateRealValue(double amount);

    private double validatePrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Cena aktywa nie może być ujemna.");
        }
        return price;
    }

}