package com.stockmarket.portfolio;

import com.stockmarket.exceptions.NotEnoughCashException;

public class Portfolio {

    private double cash;
    private HoldingsWallet holdingsWallet;

    public Portfolio(double initialCash) {
        this.cash = validateCashAmount(initialCash);
        this.holdingsWallet = new HoldingsWallet();
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

    public void addStock(Stock stock, int quantity){
        holdingsWallet.addHolding(stock, quantity);
    }
    
    public int getTotalHoldingsCount() {
        int count = 0;
        for (StockHolding holding : holdingsWallet.getAllHoldings()) {
            count += holding.getQuantity();
        }
        return count;
    }

    public double calculateStockValue() {
        double total = 0.0;
        for (StockHolding holding : this.holdingsWallet.getAllHoldings()) {
            total += holding.calculateValue();
        }
        return total;
    }

    public double calculatePortfolioValue() {
        return this.cash + this.calculateStockValue();
    }

    private double validateCashAmount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Kwota gotówki nie może być ujemna.");
        }
        return amount;
    }
}
