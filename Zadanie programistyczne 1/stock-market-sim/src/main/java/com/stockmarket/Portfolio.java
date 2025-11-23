package com.stockmarket;

import java.util.logging.SocketHandler;

import com.stockmarket.exceptions.NotEnoughCashException;
import com.stockmarket.exceptions.PortfolioWalletISFullException;
import com.stockmarket.exceptions.StockNotFoundInHoldingsException;

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

    public int getTotalHoldingsCount() {
        int count = 0;
        for (StockHolding holding : holdingsWallet) {
            count += holding.getQuantity();
        }
    }

    public void addStock(Stock stock, int quantity) throws PortfolioWalletISFullException{
        holdingsWallet.addHolding(stock, quantity);
    }

    public double calculateTotalStockValue() {
        double total = 0.0;
        for (StockHolding holding : this.holdingsWallet.getAllHoldings()) {
            total += holding.calculateValue();
        }
        return total;
    }

    public double calculateTotalValue() {
        return this.cash + this.calculateTotalStockValue();
    }

    private double validateCashAmount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Kwota gotówki nie może być ujemna.");
        }
        return amount;
    }
}
