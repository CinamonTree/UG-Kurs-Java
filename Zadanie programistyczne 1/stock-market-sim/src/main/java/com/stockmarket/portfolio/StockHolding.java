package com.stockmarket.portfolio;

/**
* Klasa StockHolding reprezentuję posiadane akcje danego typu w portfelu inwestycyjnym.
*/

public class StockHolding {

    private Stock stock;
    private int quantity;

    public StockHolding(Stock stock, int quantity) {
        this.stock = validateStock(stock);
        this.quantity = validateQuantity(quantity);
    }

    public Stock getStock() {
        return this.stock;
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

    public double calculateValue() {
        return quantity * stock.getValue();
    }

    private int validateQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Ilość posiadanych akcji nie może być ujemna");
        }
        return quantity;
    }

    private Stock validateStock(Stock stock) {
        if (stock == null) {
            throw new IllegalArgumentException("Podano argument null dla parametru konstruktora obiektu.");
        }
        return stock;
    }

}
