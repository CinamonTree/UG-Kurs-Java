package com.stockmarket;

import com.stockmarket.exceptions.PortfolioWalletISFullException;

public class Portfolio {

    private double cash;
    private StockHolding[] holdings;
    private int holdingsCount;

    private static class StockHolding {
        private Stock stock;
        private int quantity;

        public StockHolding(Stock stock, int quantity) {
            validateStock(stock);
            validateQuantity(quantity);
            this.stock = stock;
            this.quantity = quantity;
        }

        private void validateQuantity(int quantity) {
            if (quantity < 0) {
                throw new IllegalArgumentException("Ilość posiadanych akcji nie może być ujemna");
            }
        }

        private void validateStock(Stock stock) {
            if (stock == null) {
                throw new IllegalArgumentException("Podano argument null dla parametru konstruktora obiektu.");
            }
        }
    }

    public Portfolio(double initialCash) {
        this.cash = initialCash;
        this.holdings = new StockHolding[10];
        this.holdingsCount = 0;
    }

    public double getCash() {
        return this.cash;
    }

    public double addCash(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Nie można dodać ujemnej ilości gotówki do portfela.");
        }
        this.cash += amount;
        return this.cash;
    }

    public double withdrawCash(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Nie można wypłacić ujemnej ilości gotówki z portfela.");
        }
        if (amount > this.cash) {
            throw new IllegalArgumentException("Nie można wypłacić więcej gotówki niż jest dostępne w portfelu.");
        }
        this.cash -= amount;
        return this.cash;
    }

    public int getHoldingsCount() {
        return this.holdingsCount;
    }

    public void addStock(Stock stock, int quantity) throws PortfolioWalletISFullException{
        if (stock == null) {
            throw new IllegalArgumentException("Metoda addStock musi przyjąć obiekt typu Stock. podano null.");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Nie można dodać ujemnej liczy akcji.");
        }
        if (isHoldingsWalletFull()) {
            throw new PortfolioWalletISFullException("Nie można dodać akcji do portfolio, portfel akcji jest pełny.");
        }

        StockHolding stockHolding = findStockHolding(stock);
        if (stockHolding != null) {
            stockHolding.quantity += quantity;
            return;
        }
        addNewStockHolding(stock, quantity);
    }

    public double calculateStockValue() {
        double total = 0.0;
        for (StockHolding holding : this.holdings) {
            if (holding != null) {
                total += holding.stock.getValue() * holding.quantity;
            }
        }
        return total;
    }

    public double calculateTotalValue() {
        return this.cash + this.calculateStockValue();
    }

    public int getStockQuantity(Stock stock) {
        for (StockHolding holding : this.holdings) {
            if (holding != null && holding.stock.equals(stock)) {
                return holding.quantity;
            }
        }
        return 0;
    }

    private boolean isHoldingsWalletFull() {
        return this.holdingsCount >= this.holdings.length;
    }

    private StockHolding findStockHolding(Stock stockToFind) {
        for (int i = 0; i < holdings.length; i++) {
            if (holdings[i] != null && holdings[i].stock.equals(stockToFind)) {
                return holdings[i];
            }
        }
        return null;
    }

    private void addNewStockHolding(Stock stock, int quantity) {
        this.holdings[this.holdingsCount] = new StockHolding(stock, quantity);
        this.holdingsCount++;
    }
}
