package com.stockmarket.portfolio;

import com.stockmarket.domain.Money;
import com.stockmarket.exceptions.NotEnoughFundsException;

class CashWallet {

    private Money cash;

    public CashWallet(Money initialCash) {
        this.cash = initialCash;
    }

    public Money getMoney() {
        return cash;
    }

    public void deposit(Money deposit) {
        deposit = validateMoney(deposit);
        cash = cash.add(deposit);
    }

    public void withdraw(Money withdraw) {
        withdraw = validateMoney(withdraw);
        if (withdraw.getAmount() > cash.getAmount()) {
            throw new NotEnoughFundsException("Niewystarczające środki w portfelu do wypłaty.");
        }
        cash = cash.subtract(withdraw);
    }

    private Money validateMoney(Money amount) {
        if (!cash.getCurrency().equals(amount.getCurrency())) {
            throw new IllegalArgumentException("Nie można dodać innego rodzaju waluty do portfela.");
        }
        if (amount.getAmount() <= 0) {
            throw new IllegalArgumentException("Nie można wypłacić lub wpłacić niedodatniej kwoty do portfela.");
        }
        return amount;
    }
}