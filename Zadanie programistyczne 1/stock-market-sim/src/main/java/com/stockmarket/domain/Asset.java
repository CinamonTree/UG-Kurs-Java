package com.stockmarket.domain;

public abstract class Asset {

    private Money price;
    private String code;

    public Asset(String code, Money price) {
        this.code = validateAssetCode(code);
        this.price = validatePrice(price);
    }

    public Money getPrice() {
        return price;
    }

    public String getCode() {
        return code;
    }
    
    public void setPrice(Money price) {
        this.price = validatePrice(price);
    }

    public void setCode(String code) {
        this.code = validateAssetCode(code);
    }

    public abstract Money getRealPrice(int assetQuantity);

    private Money validatePrice(Money price) {
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
