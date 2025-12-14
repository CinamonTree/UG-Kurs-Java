package com.stockmarket.portfolio;

import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.stockmarket.domain.Money;

class CashWalletTest {

    private CashWallet cashWallet;

    @BeforeEach
    void setUp() {
        cashWallet = new CashWallet(new Money(Currency.getInstance("USD"), 1000.0));
    }

    // Konstruktor

    @Test
    void shouldCreateCashWallet() {
        Money money = cashWallet.getMoney();
        assertEquals(1000.0, money.getAmount());
        assertEquals("USD", money.getCurrency().getCurrencyCode());
    }

    // Metoda deposit

    @Test
    void shouldDepositMoney() {
        Money depositAmount = new Money(Currency.getInstance("USD"), 500.0);
        cashWallet.deposit(depositAmount);
        assertEquals(1500.0, cashWallet.getMoney().getAmount());
    }

    // Metoda withdraw

    @Test
    void testWithdrawMoney() {
        Money withdrawAmount = new Money(Currency.getInstance("USD"), 300.0);
        cashWallet.withdraw(withdrawAmount);
        assertEquals(700.0, cashWallet.getMoney().getAmount());
    }

    @Test
    void shouldThrowExceptionWhenNotEnoughMoneyToWithdraw() {
        Money withdrawAmount = new Money(Currency.getInstance("USD"), 1500.0);
        assertThrows(
            com.stockmarket.exceptions.NotEnoughFundsException.class,
            () -> cashWallet.withdraw(withdrawAmount)
        );
    }
}