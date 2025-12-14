package com.stockmarket.portfolio;

import com.stockmarket.domain.Asset;
import com.stockmarket.domain.Money;

/**
* Klasa StockHolding reprezentuję posiadane akcje danego typu w portfelu inwestycyjnym.
*/

public class Position {

    private Asset asset;
    private int quantity;

    public Position(Asset asset, int quantity) {
        this.asset = validateAsset(asset);
        this.quantity = validateQuantity(quantity);
    }

    public Asset getAsset() {
        return this.asset;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public int addQuantity(int amount) {
        int validAmount = validateQuantity(amount);
        this.quantity += validAmount;
        return this.quantity;
    }

    public int reduceQuantity(int amount) {
        int validAmount = validateQuantity(amount);
        if (validAmount > this.quantity) {
            throw new IllegalArgumentException("Nie można zmniejszyć ilości posiadanych akcji poniżej zera.");
        }
        this.quantity -= validAmount;
        return this.quantity;
    }

    public Money getPositionPrice() {
        Money assetPrice = asset.getPrice();
        return assetPrice.multiply(quantity);
    }

    private int validateQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Ilość posiadanych akcji nie może być ujemna");
        }
        return quantity;
    }

    private Asset validateAsset(Asset asset) {
        if (asset == null) {
            throw new IllegalArgumentException("Podano argument null dla parametru konstruktora obiektu.");
        }
        return asset;
    }

}
