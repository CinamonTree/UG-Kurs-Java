package com.stockmarket.domain;

public abstract class Asset {

    private double price;
    private String code;

    public Asset(String code, double price) {
        this.code = validateAssetCode(code);
        this.price = validatePrice(price);
    }

    public double getPrice() {
        return price;
    }

    public String getCode() {
        return code;
    }
    
    public void setPrice(double price) {
        this.price = validatePrice(price);
    }

    public void setCode(String code) {
        this.code = validateAssetCode(code);
    }

    public abstract double getRealPrice();

    private double validatePrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Cena aktywa nie może być ujemna.");
        }
        return price;
    }

    private String validateAssetCode(String code) {
        if (code == null || code.trim().isEmpty() ) {
            throw new IllegalArgumentException("Kod nie może być pustym ciągiem znaków lub nullem!");
        }
        return code;
    }

}
