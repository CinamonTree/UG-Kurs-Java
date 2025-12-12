package com.stockmarket.domain;

public abstract class Asset {

    private Price price;
    private String code;

    public Asset(String code, Price price) {
        this.code = validateAssetCode(code);
        this.price = validatePrice(price);
    }

    public Price getPrice() {
        return price;
    }

    public String getCode() {
        return code;
    }
    
    public void setPrice(Price price) {
        this.price = validatePrice(price);
    }

    public void setCode(String code) {
        this.code = validateAssetCode(code);
    }

    public abstract double getRealPrice();

    private Price validatePrice(Price price) {
        if (price.getAmount() < 0) {
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

    @Override
    public String toString() {
        return code;
    }

    @Override
    public boolean equals(Object another) {
        if (this == another) return true;
        if (!(another instanceof Asset)) return false;
        Asset other = (Asset) another;
        return code.equals(other.code);
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }

}
