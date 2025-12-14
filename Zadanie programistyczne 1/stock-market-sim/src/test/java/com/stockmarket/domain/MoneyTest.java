package com.stockmarket.domain;

import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoneyTest {
    
    private Money USD10;
    private Money USD20;
    private Money EUR10;
    private Currency USD;
    private Currency EUR;

    @BeforeEach
    public void setUp() {
        USD = Currency.getInstance("USD");
        EUR = Currency.getInstance("EUR");
        USD10 = new Money(USD, 10.0);
        USD20 = new Money(USD, 20.0);
        EUR10 = new Money(EUR, 10.0);
    }
        
    // Konstruktor

    @Test
    public void shouldCreatePrice() {
        Money price = new Money(USD, 10.0);

        assertEquals(USD, price.getCurrency());
        assertEquals(10.0, price.getAmount());
    }

    @Test
    public void shouldThrowExceptionWhenCreatingPriceWithNegativeAmount() {
        assertThrows(IllegalArgumentException.class,() -> {
            new Money(USD, -5.0);
        });
    }

    @Test
    public void shouldThrowExceptionWhenCreatingPriceWithNullPointerCurrency() {
        assertThrows(IllegalArgumentException.class,() -> {
            new Money(null, 10.0);
        });
    }

    // Metoda add

    public void shouldAddMoneyCorrectly() {
        Money result = USD10.add(USD20);
        assertEquals(30.0, result.getAmount());
        assertEquals(USD, result.getCurrency());
    }

    @Test
    public void shouldThrowExceptionWhenAddingPricesWithDifferentCurrencies() {
        assertThrows(IllegalArgumentException.class, () -> {
            USD10.add(EUR10);
        });
    }

    // metoda subtract

    @Test
    public void shouldSubtractMoneyCorrectly() {
        Money result = USD20.subtract(USD10);
        assertEquals(10.0, result.getAmount());
        assertEquals(USD, result.getCurrency());
    }

    @Test
    public void shouldThrowExceptionWhenSubtractingPricesWithDifferentCurrencies() {
        assertThrows(IllegalArgumentException.class, () -> {
            USD10.subtract(EUR10);
        });
    }

    // metoda multiply

    @Test
    public void shouldMultiplyMoney() {
        Money result = USD20.multiply(USD10);
        assertEquals(200.0, result.getAmount());
        assertEquals(USD, result.getCurrency());
    }

    @Test
    public void shouldThrowExceptionWhenMultiplyingPricesWithDifferentCurrencies() {
        assertThrows(IllegalArgumentException.class, () -> {
            USD10.subtract(EUR10);
        });
    }

    // metoda multiply (int)

    @Test
    public void shouldMultiplyMoneyWithInteger() {
        Money result = USD20.multiply(5);
        assertEquals(100.0, result.getAmount());
        assertEquals(USD, result.getCurrency());
    }

    // Metoda equals

    @Test
    public void shouldComparePricesByCurrencyAndAmount() {
        Money anotherUSD10 = new Money(USD, 10.0);
        assertEquals(USD10, anotherUSD10);
    }

    @Test
    public void shouldNotConsiderPricesWithDifferentCurrenciesAsEqual() {
        Money anotherEUR10 = new Money(EUR, 10.0);
        assertEquals(false, USD10.equals(anotherEUR10));
    }

    @Test
    public void shouldNotConsiderPricesWithDifferentAmountsAsEqual() {
        Money anotherUSD15 = new Money(USD, 15.0);
        assertEquals(false, USD10.equals(anotherUSD15));
    }

    @Test
    public void shouldReturnFalseWhenComparingWithOtherClass() {
        assertFalse(USD10.equals(new Object()));
    }

    @Test
    public void shouldReturnFalseWhenComparingWithNull() {
        assertFalse(USD10.equals(null));
    }

    // Metoda hashCode

    @Test
    public void shouldGenerateHashCodeBasedOnCurrencyAndAmount() {
        Money anotherUSD10 = new Money(USD, 10.0);
        assertEquals(USD10.hashCode(), anotherUSD10.hashCode());
    }

    // Metoda compareTo

    @Test
    public void shouldComparePricesCorrectly() {
        assertEquals(-1, USD10.compareTo(USD20));
        assertEquals(1, USD20.compareTo(USD10));
        assertEquals(0, USD10.compareTo(new Money(USD, 10.0)));
    }

    @Test
    public void shouldThrowExceptionWhenComparingPricesWithDiffrentCurrencies() {
        assertThrows(IllegalArgumentException.class, () -> {
            USD10.compareTo(EUR10);
        });
    }

    // Metoda toString

    @Test
    public void shouldReturnStringRepresentationOfPrice() {
        assertEquals("10.0 USD", USD10.toString());
    }

}
