package com.stockmarket;

import com.stockmarket.exceptions.NotEnoughCashException;
import com.stockmarket.exceptions.PortfolioWalletISFullException;

public class Portfolio {

    private double cash;
    private StockHolding[] holdings;
    private int holdingsCount;

    public Portfolio(double initialCash) {
        this.cash = validateCashAmount(initialCash);
        this.holdings = new StockHolding[10];
        this.holdingsCount = 0;
    }

    public double getCash() {
        return this.cash;
    }

    public double addCash(double amount) {
        amount = validateCashAmount(amount);
        this.cash += amount;
        return this.cash;
    }

    public double withdrawCash(double amount) {
        double validAmount = validateCashAmount(amount);

        if (validAmount > this.cash) {
            throw new NotEnoughCashException("Nie można wypłacić więcej gotówki niż jest dostępne w portfelu.");
        }
        
        this.cash -= validAmount;
        return this.cash;
    }

    public int getHoldingsCount() {
        return this.holdingsCount;
    }

    public void addStock(Stock stock, int quantity) throws PortfolioWalletISFullException{
        if (stock == null) {
            throw new IllegalArgumentException("Metoda addStock musi przyjąć obiekt typu Stock. podano null.");
        }
        if (isHoldingsWalletFull()) {
            throw new PortfolioWalletISFullException("Nie można dodać akcji do portfolio, portfel akcji jest pełny.");
        }

        StockHolding stockHolding = findStockHolding(stock);
        if (stockHolding != null) {
            stockHolding.addQuantity(quantity);
            return;
        }
        addNewStockHolding(stock, quantity);
    }

    public double calculateStockValue() {
        double total = 0.0;
        for (StockHolding holding : this.holdings) {
            if (holding != null) {
                total += holding.getStock().getValue() * holding.getQuantity();
            }
        }
        return total;
    }

    public double calculateTotalValue() {
        return this.cash + this.calculateStockValue();
    }

    public int getStockQuantity(Stock stock) {
        for (StockHolding holding : this.holdings) {
            if (holding != null && holding.getStock().equals(stock)) {
                return holding.getQuantity();
            }
        }
        return 0;
    }

    private boolean isHoldingsWalletFull() {
        return this.holdingsCount >= this.holdings.length;
    }

    private StockHolding findStockHolding(Stock stockToFind) {
        for (StockHolding holding : holdings) {
            if (holding != null && holding.getStock().equals(stockToFind)) {
                return holding;
            }
        }
        return null;
    }

    private void addNewStockHolding(Stock stock, int quantity) {
        this.holdings[this.holdingsCount] = new StockHolding(stock, quantity);
        this.holdingsCount++;
    }

    private double validateCashAmount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Kwota gotówki nie może być ujemna.");
        }
        return amount;
    }
}
