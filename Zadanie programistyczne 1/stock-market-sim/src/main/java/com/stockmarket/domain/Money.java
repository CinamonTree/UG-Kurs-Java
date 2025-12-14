package com.stockmarket.domain;

import java.util.Currency;

public class Money implements Comparable<Money> {
    
    private final Currency currency;
    private final double amount;

    public Money(Currency currency, double amount) {
        this.currency = validateCurrency(currency);
        this.amount = validateAmount(amount);
    }

    public Money add(Money other) {
        boolean differentCurrencies = !this.currency.equals(other.currency);
        if (differentCurrencies) {
            throw new IllegalArgumentException(
                "Nie można dodać cen w różnych walutach." 
            );
        }
        return new Money(this.currency, this.amount + other.amount);
    }

    public Money subtract(Money other) {
        boolean differentCurrencies = !this.currency.equals(other.currency);
        if (differentCurrencies) {
            throw new IllegalArgumentException(
                "Nie można odjąć cen w różnych walutach." 
            );
        }
        return new Money(this.currency, this.amount - other.amount);
    }

    public Money multiply(Money other) {
        boolean differentCurrencies = !this.currency.equals(other.currency);
        if (differentCurrencies) {
            throw new IllegalArgumentException(
                "Nie można dodać cen w różnych walutach." 
            );
        }
        return new Money(this.currency, this.amount * other.amount);
    }

    public Money multiply(int amount) {
        return new Money(this.currency, this.amount * amount);
    }

    public Currency getCurrency() {
        return currency;
    }

    public double getAmount() {
        return amount;
    }

    private Currency validateCurrency(Currency currency) {
        if (currency == null) {
            throw new IllegalArgumentException("Waluta nie może być nullem.");
        }
        return currency;
    }

    private double validateAmount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Kwota nie może być ujemna.");
        }
        return amount;
    }

    @Override
    public String toString() {
        return amount + " " + currency.getCurrencyCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Money other = (Money) obj;
        return amount == other.amount && currency.equals(other.currency);
    }

    @Override
    public int hashCode() {
        int currencyHash = currency.hashCode(); // hasz z waluty
        long amountAsLong = Double.doubleToLongBits(amount); // zmiana amount z double na long
        int amountHash = (int) (amountAsLong ^ (amountAsLong >>> 32)); // hasz z amount
        int Hash = 31 * currencyHash + amountHash; // kombinacja haszy
        return Hash;
    }

    @Override
    public int compareTo(Money other) {
        boolean differentCurrencies = !this.currency.equals(other.currency);
        if (differentCurrencies) {
            throw new IllegalArgumentException(
                "Nie można porównać cen w różnych walutach." 
            );
        }
        return Double.compare(this.amount, other.amount);
    }
    
}
