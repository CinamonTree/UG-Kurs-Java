package com.stockmarket.portfolio;

import java.util.Currency;

import com.stockmarket.domain.Asset;
import com.stockmarket.domain.Money;

public class Portfolio {

    private final CashWallet cashWallet;
    private final PositionsBook positionsBook;

    public Portfolio(double initialCashAmmount, Currency currency) {
        currency = validateCurrency(currency);
        initialCashAmmount = validateInitialCashAmount(initialCashAmmount);
        this.cashWallet = new CashWallet(new Money(currency, initialCashAmmount));
        this.positionsBook = new PositionsBook();
    }

    public CashWallet getCashWallet() {
        return cashWallet;
    }

    public PositionsBook getPositionsBook() {
        return positionsBook;
    }

    public void depositCash(Money amount) {
        this.cashWallet.deposit(amount);
    }

    // TODO: Wymiana waluty przy zakupie

    public void withdrawCash(Money amount) {
        this.cashWallet.withdraw(amount);
    }

    public void buyAsset(Asset asset, int quantity) {
        Money assetTotalPrice = asset.getRealPrice(quantity);
        withdrawCash(assetTotalPrice);
        positionsBook.addPosition(asset, quantity);
    }

    public void sellAsset(Asset asset, int quantity) {
        Money assetTotalPrice = asset.getPrice().multiply(quantity);
        depositCash(assetTotalPrice);
        positionsBook.reducePosition(asset, quantity);
    }

    private double validateInitialCashAmount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Początkowa kwota gotówki w portfelu nie może być ujemna.");
        }
        return amount;
    }

    private Currency validateCurrency(Currency currency) {
        if (currency == null) {
            throw new IllegalArgumentException("Waluta nie może być nullem.");
        }
        return currency;
    }

}
